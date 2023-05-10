package com.example.assignmentsheet4.personalWebBrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import com.example.assignmentsheet4.R
import com.google.android.material.textfield.TextInputEditText

class PersonBrowserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_browser)

        val txtUrl = findViewById<TextInputEditText>(R.id.txtUrl)
        val btnSearch = findViewById<Button>(R.id.btngo)
        val webView = findViewById<WebView>(R.id.webv)
        val buttonBack = findViewById<Button>(R.id.btnBack)
        val buttonForward = findViewById<Button>(R.id.btnForward)

        btnSearch.setOnClickListener {
            val url = txtUrl.text.toString()
            Log.i("AppDev",url)
            webView.loadUrl(url)
        }

        buttonBack.setOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
            }
        }

        buttonForward.setOnClickListener {
            if (webView.canGoForward()) {
                webView.goForward()
            }
        }

        //? handles the interaction with the browser, e.g. when clicking on a Link => updates the WebView
        val webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }
        }


        webView.webViewClient = webViewClient
    }
}