package com.example.tugas.ui.webview

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.example.tugas.R
import com.example.tugas.databinding.ActivityWebviewBinding

class WebviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val bundle = intent.extras
        val title = bundle?.getString(EXTRA_TITLE, resources.getString(R.string.app_name))
        val url = bundle?.getString(EXTRA_URL, resources.getString(R.string.def_url))

        binding.toolbar.title = title ?: resources.getString(R.string.app_name)

        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(url ?: resources.getString(R.string.def_url))
    }

    // Overriding WebViewClient functions
    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            binding.progressBar2.visibility = View.GONE
        }
    }

    companion object {
        val EXTRA_URL = "URL"
        val EXTRA_TITLE = "TITLE"
    }
}