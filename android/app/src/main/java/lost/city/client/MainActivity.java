package lost.city.client;


import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

import com.getcapacitor.BridgeActivity;
import com.getcapacitor.PluginHandle;

public class MainActivity extends BridgeActivity {

    private WebView webview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBridge().registerPlugin(LongPressPlugin.class);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        webview = findViewById(com.getcapacitor.android.R.id.webview); // Replace with the ID of your root layout
        webview.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            Rect r = new Rect();
            Window window = getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(r);
            int screenHeight = window.getDecorView().getHeight();
            int visibleHeight = r.bottom - r.top;

            int keyboardHeight = screenHeight - visibleHeight;

            if (keyboardHeight > screenHeight / 3) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) webview.getLayoutParams();
                params.bottomMargin = keyboardHeight;
                webview.setLayoutParams(params);
            } else {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) webview.getLayoutParams();
                params.bottomMargin = 0;
                webview.setLayoutParams(params);
            }
        });
        webview.setOnLongClickListener(v -> {
            PluginHandle plugin = getBridge().getPlugin("LongPress");
                    ((LongPressPlugin)plugin.getInstance()).handleLongPress(v);
            return true;
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }
}
