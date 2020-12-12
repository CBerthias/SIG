package com.projetetu.sig;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class PlanActivity extends AppCompatActivity {

    WebView webView;

    public String fileName = "map.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        webView = (WebView) findViewById(R.id.simpleWebView);
        webView.loadUrl("file:///android_asset/" + fileName);
        // displaying content in WebView from html file that stored in assets folder
        webView.getSettings().setJavaScriptEnabled(true);

    }
}