package com.awkris.test.gitter.view.webview

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.awkris.test.gitter.R
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity() {

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        url = requireNotNull(intent.extras).getString("url")
        webview.settings.domStorageEnabled = true
        webview.overScrollMode = WebView.OVER_SCROLL_NEVER
        webview.loadUrl(url)
    }
}