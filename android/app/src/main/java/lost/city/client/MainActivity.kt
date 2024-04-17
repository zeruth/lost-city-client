package lost.city.client

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.webkit.WebView
import com.getcapacitor.BridgeActivity
import com.getcapacitor.android.R

class MainActivity : BridgeActivity() {
    lateinit var webview: WebView
    lateinit var longPressPlugin: LongPressPlugin
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerCapacitorPlugins()
        webview = findViewById(R.id.webview)
        webview.getViewTreeObserver().addOnGlobalLayoutListener(::adjustViewForKeyboard)
        webview.setOnLongClickListener(::handleLongPress)
    }

    fun registerCapacitorPlugins() {
        getBridge().registerPlugin(LongPressPlugin::class.java)
        longPressPlugin = getBridge().getPlugin("LongPress").instance as LongPressPlugin
    }

    private fun handleLongPress(webView: View): Boolean {
        longPressPlugin.handleLongPress(webView)
        return true
    }

    private fun adjustViewForKeyboard() {
        val r = Rect()
        val window = window
        window.decorView.getWindowVisibleDisplayFrame(r)
        val screenHeight = window.decorView.height
        val visibleHeight = r.bottom - r.top
        val keyboardHeight = screenHeight - visibleHeight
        if (keyboardHeight > screenHeight / 3) {
            val params = webview.layoutParams as MarginLayoutParams
            params.bottomMargin = keyboardHeight
            webview.layoutParams = params
        } else {
            val params = webview.layoutParams as MarginLayoutParams
            params.bottomMargin = 0
            webview.layoutParams = params
        }
    }
}
