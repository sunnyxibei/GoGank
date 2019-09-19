package com.timeriver.gogank.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.timeriver.gogank.R

class BrowserActivity : AppCompatActivity() {

    companion object {
        private const val MAX = 100
        private const val BROWSER_URL = "browser_url"

        /**
         * 默认requestCode=-1不提供返回结果
         */
        fun start(activity: Activity, url: String, requestCode: Int = -1) {
            val intent = Intent(activity, BrowserActivity::class.java)
            intent.putExtra(BROWSER_URL, url)
            activity.startActivityForResult(intent, requestCode)
        }
    }

    private var mBrowserUrl: String? = null
    private lateinit var mWebView: WebView

    private lateinit var mProgressBar: ProgressBar
    private lateinit var mWebContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)
        mProgressBar = findViewById(R.id.progress_bar)
        mWebContainer = findViewById(R.id.fl_web_container)

        initData()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initData() {
        mBrowserUrl = intent.getStringExtra(BROWSER_URL)

        mWebView = WebView(this)
        mWebContainer.addView(mWebView)
        mWebView.requestFocus()
        mWebView.isHorizontalScrollBarEnabled = false
        mWebView.isVerticalScrollBarEnabled = false
        mWebView.overScrollMode = WebView.OVER_SCROLL_NEVER
        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.domStorageEnabled = true
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    return false
                }
                // Otherwise allow the OS to handle things like tel, mailto, etc.
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
                return true
            }
        }
        mWebView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                mProgressBar.progress = newProgress
                if (newProgress == MAX) {
                    mProgressBar.visibility = View.GONE
                } else {
                    mProgressBar.visibility = View.VISIBLE
                }
            }
        }
        mWebView.loadUrl(mBrowserUrl)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        mWebView.removeAllViews()
        mWebView.clearHistory()
        mWebView.clearCache(true)
        mWebView.destroy()
    }
}