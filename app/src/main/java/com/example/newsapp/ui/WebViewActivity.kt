package com.example.newsapp.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.newsapp.base.BaseActivity
import com.example.newsapp.databinding.ActivityWebViewBinding

class WebViewActivity : BaseActivity() {

    private lateinit var mBinding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val url = intent.getStringExtra("URL")
        val title = intent.getStringExtra("TITLE")
        mBinding.wvBrowser.settings.loadWithOverviewMode = true
        mBinding.wvBrowser.settings.useWideViewPort = true
        mBinding.wvBrowser.settings.javaScriptEnabled = true
        mBinding.wvBrowser.webViewClient = WebClient()
        url?.let { mBinding.wvBrowser.loadUrl(it) }
        mBinding.tvNewsTitle.text = title

        mBinding.toolbarWebView.setNavigationOnClickListener {
            onBackPressed()
        }
        mBinding.tvNewsTitle.performClick()

        mBinding.wvBrowser.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action and KeyEvent.ACTION_DOWN == 0) {
                    val webView = v as WebView
                    when (keyCode) {
                        KeyEvent.KEYCODE_BACK -> if (webView.canGoBack()) {
                            webView.goBack()
                            return true
                        }
                    }
                }
                return false
            }
        })

        mBinding.tvNewsTitle.setOnClickListener {
            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show()
        }
    }

    inner class WebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            mBinding.progressBar.visibility = View.VISIBLE
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            mBinding.progressBar.visibility = View.GONE
        }
    }
}