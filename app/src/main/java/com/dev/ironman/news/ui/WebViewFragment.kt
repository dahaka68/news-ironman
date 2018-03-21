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
import android.widget.FrameLayout
import com.dev.ironman.news.App
import com.dev.ironman.news.R
import com.dev.ironman.news.mvp.presenters.WebFragmentPresenter
import com.dev.ironman.news.mvp.views.WebFragmentView
import kotlinx.android.synthetic.main.fragment_web_view.view.*
import javax.inject.Inject


class WebViewFragment : Fragment(), WebFragmentView {

    @Inject
    lateinit var webFragPresenter: WebFragmentPresenter

    private lateinit var webView: WebView
    private lateinit var progressBar: FrameLayout
    private var url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App().daggerComponent.inject(this)
        url = arguments?.getString("URL") ?: ""
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_web_view, container, false)
        webView = view.webview
        progressBar = view.prBarWeb
        webView.webViewClient = SimpleWebViewClient()

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (progress == 100) {
                    progressBar.visibility = View.GONE
                }
            }
        }

        webFragPresenter.attachView(this)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        webFragPresenter.detachView()
    }

    override fun showContent() {
        if (!url.equals("")) webView.loadUrl(url)
    }

    class SimpleWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return false
        }
    }

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
