package com.dev.ironman.news.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dev.ironman.news.R
import com.dev.ironman.news.daggerComponent
import com.dev.ironman.news.mvp.presenters.WebFragmentPresenter
import com.dev.ironman.news.mvp.views.WebFragmentView
import kotlinx.android.synthetic.main.fragment_web_view.*
import javax.inject.Inject

class WebViewFragment : Fragment(), WebFragmentView {

    @Inject
    lateinit var webFragPresenter: WebFragmentPresenter

    private var url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        daggerComponent.inject(this)
        url = arguments?.getString("URL") ?: ""
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWebViewClients()
        webFragPresenter.attachView(this)
    }

    private fun setWebViewClients() {
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }
        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (progress == 100) {
                    webFragPresenter.hideProgress()
                }
            }
        }
    }

    override fun hideProgress() {
        prBarWeb.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        webFragPresenter.detachView()
    }

    override fun showContent() {
        if (url != "") webview.loadUrl(url)
    }

    //webViewCache
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        webView.saveState(outState)
//    }
//
//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        webView.restoreState(savedInstanceState)
//    }
}
