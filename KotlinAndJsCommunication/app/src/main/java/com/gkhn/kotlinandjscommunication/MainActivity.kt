package com.gkhn.kotlinandjscommunication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gkhn.kotlinandjscommunication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webview.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            settings.builtInZoomControls = true
            settings.displayZoomControls = false
            addJavascriptInterface(this@MainActivity, "Android")
            loadUrl("file:///android_asset/html/index.html")
        }

        binding.btnSubmit.setOnClickListener {
            val data = binding.edtMain.text.toString()
            binding.webview.evaluateJavascript("onSubmit('${data}')", null)
        }
    }

    override fun onBackPressed() {
        if (binding.webview.canGoBack()) {
            binding.webview.goBack()
            binding.containerMain.visibility = View.VISIBLE
        } else {
            super.onBackPressed()
        }
    }

    //Javascript Interface Methods
    @JavascriptInterface
    fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun submitToActivity(text: String) {
        binding.edtMain.setText(text)
    }

    @JavascriptInterface
    fun changePage() {
        binding.containerMain.visibility = View.GONE
    }
}
