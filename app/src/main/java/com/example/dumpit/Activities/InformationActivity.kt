package com.example.dumpit.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.dumpit.R
import com.example.dumpit.databinding.ActivityInformationBinding
import com.example.dumpit.databinding.ActivityInputWasteBinding

class InformationActivity : AppCompatActivity() {
    val binding:ActivityInformationBinding by lazy{
      ActivityInformationBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Set up WebView
      //  val webView:WebView = findViewById(R.id.webView)
        binding.webView.webViewClient = WebViewClient()
        loadEducationalResources()

        // Your existing code
        // ...
    }

    private fun loadEducationalResources() {
        // Load a sample educational page. Replace the URL with your actual educational content.
        val educationalUrl = "https://www.kelvinindia.in/blog/10-reasons-why-waste-management-is-important/"
        binding.webView.loadUrl(educationalUrl)
    }

}
