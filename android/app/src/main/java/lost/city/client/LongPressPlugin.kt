package lost.city.client

import android.util.Log
import android.view.View
import android.webkit.WebView
import com.getcapacitor.Plugin
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin

@CapacitorPlugin(name = "LongPress")
class LongPressPlugin : Plugin() {
    @PluginMethod
    fun handleLongPress(v: View) {
        val webView = v as WebView
        webView.post {
            webView.evaluateJavascript(
                "(function() {" +
                        "    var canvas = document.getElementById('canvas');" +
                        "    if (canvas) {" +
                        "        var evt = new MouseEvent('mousedown', {" +
                        "    buttons: 2" +
                        "});" +
                        "        canvas.dispatchEvent(evt);" +
                        "        return 'Success';" +
                        "    } else {" +
                        "        return 'Failure';" +
                        "    }" +
                        "})();"
            ) { value: String -> Log.d("WebViewEvent", "Android(LongPress) Result: $value") }
        }
    }
}