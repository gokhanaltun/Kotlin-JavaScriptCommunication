package com.gkhn.kotlinandjscommunication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
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
            addJavascriptInterface(JavascriptInterface(this@MainActivity), "inter")
            loadUrl("file:///android_asset/tr-map.html")
        }
    }

}

class JavascriptInterface(var context: Context){

    @android.webkit.JavascriptInterface
    fun showToast(text: String){
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
    }

}