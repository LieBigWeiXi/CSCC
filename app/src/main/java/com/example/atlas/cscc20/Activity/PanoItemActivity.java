package com.example.atlas.cscc20.Activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.atlas.cscc20.R;


public class PanoItemActivity extends BaseActivity {
    WebView pano_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pano_layout);
        pano_view=(WebView)findViewById(R.id.pano_view);
        WebSettings s = pano_view.getSettings();
        s.setJavaScriptEnabled(true);
        s.setPluginState(WebSettings.PluginState.ON);
        s.setLoadWithOverviewMode(true);
        s.setDomStorageEnabled(true);
        pano_view.setWebViewClient(new WebViewClient());
        pano_view.addJavascriptInterface(new Object(){
            @JavascriptInterface
            public void getJS(String s) {
                finish();
            }
        },"wv");
        pano_view.loadUrl("http://10.82.31.161:8002/tour/e64fed7f1236d575");
        findViewById(R.id.pano_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pano_view.loadUrl("http://10.82.31.161:8002/");
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
