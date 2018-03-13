package com.example.swain.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by swain on 2/13/18.
 */

public class WebViewTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_test);

        WebView myWebView = findViewById(R.id.testWebView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
//        myWebView.loadUrl("https://www.google.com");
        myWebView.loadData("<html><h1>Hello World!</h1><p>What the fuck</p></html>","text/html","UTF-8");
    }
}
