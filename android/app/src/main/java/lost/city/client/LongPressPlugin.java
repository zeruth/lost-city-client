package lost.city.client;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "LongPress")
public class LongPressPlugin extends Plugin {
    @PluginMethod
    public void handleLongPress(View v) {
        WebView webView = (WebView) v;
        webView.post(() -> webView.evaluateJavascript(
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
                        "})();",
                value -> Log.d("WebViewEvent", "Android(LongPress) Result: " + value)
        ));
    }
}