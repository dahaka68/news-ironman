package com.dev.ironman.news.ui

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import com.dev.ironman.news.R
import com.dev.ironman.news.util.daggerComponent
import com.dev.ironman.news.mvp.presenters.MainActivityPresenter
import com.dev.ironman.news.mvp.views.MainActivityView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.widget.ArrayAdapter
import com.dev.ironman.news.util.REFRESH


class MainActivity : AppCompatActivity(), MainActivityView {

    @Inject
    lateinit var mainPresenter: MainActivityPresenter
    private var canAddItem: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSpinners()
        daggerComponent.inject(this)
        mainPresenter.router.fragmentManager = supportFragmentManager
        getSharedPreferences(REFRESH, Context.MODE_PRIVATE).edit().putString(REFRESH, "").apply()
        btSearch.setOnClickListener({
            getSharedPreferences(REFRESH, Context.MODE_PRIVATE).edit().putString(REFRESH, "").apply()
            mainPresenter.loadNewsWithFilter()
        })
    }

    private fun initSpinners() {
        val category = ArrayAdapter.createFromResource(this,
                R.array.categories, R.layout.spinner_item)
        category.setDropDownViewResource(R.layout.spinner_dropdown_item)
        val country = ArrayAdapter.createFromResource(this,
                R.array.countries, R.layout.spinner_item)
        country.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spCategory.adapter = category
        spCountry.adapter = country
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_news_menu, menu)
        val close = menu?.findItem(R.id.close)
        if ((filterContainer.visibility == VISIBLE)) {
            close?.isVisible = true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.filterNews) {
            if (filterContainer.visibility == VISIBLE) {
                filterContainer.visibility = GONE
                invalidateOptionsMenu()
                getSharedPreferences(REFRESH, Context.MODE_PRIVATE).edit().putString(REFRESH, REFRESH).apply()
                mainPresenter.loadNewsWithFilter()
            } else {
                filterContainer.visibility = VISIBLE
                invalidateOptionsMenu()
            }
            return true
        }
        if (item?.itemId == R.id.close) {
            filterContainer.visibility = GONE
            invalidateOptionsMenu()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {

        if (canAddItem) {
            menu.getItem(1).setIcon(R.drawable.ic_done_white_24dp)
            canAddItem = false
        } else {
            menu.getItem(1).setIcon(R.drawable.ic_filter)
            canAddItem = true
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun takeFilterParams(): Map<String, String> {
        val map = HashMap<String, String>()
        map.put("category", spCategory.getSelectedItem().toString())
        map.put("country", spCountry.getSelectedItem().toString())
        map.put("q", edSeach.text.toString())
        return map
    }

    override fun isTablet(): Boolean {
        val xlarge = resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_XLARGE
        val large = resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_LARGE
        return xlarge || large
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.attachView(this)
    }

    override fun onPause() {
        super.onPause()
        mainPresenter.detachView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (!isTablet() && mainPresenter.isNotFragmentsInContainer()) {
            finish()
        }
        if (isTablet()) {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        getSharedPreferences(REFRESH, Context.MODE_PRIVATE).edit().putString(REFRESH, "").apply()
    }
}
