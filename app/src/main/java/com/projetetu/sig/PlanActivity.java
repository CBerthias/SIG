package com.projetetu.sig;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PlanActivity extends AppCompatActivity {

    private WebView webView;
    private String originalContent;
    private boolean isHere = false;
    private String xh = "";
    private String yh = "";
    private String etageh = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        webView = findViewById(R.id.simpleWebView);
        SwitchCompat switchEtage = findViewById(R.id.switchEtage);

        StringBuilder sb = new StringBuilder();
        InputStream is;
        try {
            is = getAssets().open("map.html");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8 ));
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        originalContent = sb.toString();

        String etage = this.getIntent().getStringExtra("etage");
        String x = this.getIntent().getStringExtra("x");
        String y = this.getIntent().getStringExtra("y");
        String zoom = this.getIntent().getStringExtra("zoom");
        if (!zoom.equals("21")) {
            isHere = true;
            this.xh = x;
            this.yh = y;
            this.etageh = etage;
        }
        chargePage(etage, zoom, x, y);

        if (etage.equals("etage_deux")) {
            switchEtage.setText("Etage 2");
            switchEtage.setChecked(true);
        }
    }

    private void chargePage(String couche,String zoom, String x, String y) {
        String script = "";
        if (isHere && couche.equals(this.etageh))
            script = ScriptConstant.HERE_SCRIPT;
        String content = originalContent.replace("%SCRIPT%", script);
        content = content.replace("%COUCHE%", couche);
        content = content.replace("%ZOOM%", zoom);
        content = content.replace("%X%", x);
        content = content.replace("%Y%", y);
        if (isHere && couche.equals(this.etageh)) {
            content = content.replace("%XH%", this.xh);
            content = content.replace("%YH%", this.yh);
        }

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        webView.loadDataWithBaseURL("file:///android_asset/map.html",content, "text/html", "UTF-8", null);
        webView.getSettings().setJavaScriptEnabled(true);
    }

    public void switchLayer(View view) {
        SwitchCompat switchEtage = findViewById(R.id.switchEtage);
        if (switchEtage.isChecked()) {
            chargePage("etage_deux","21", "11.5", "14.5");
            switchEtage.setText("Etage 2");
        } else {
            chargePage("etage_un","21","11.5", "14.5");
            switchEtage.setText("Etage 1");
        }
    }
}