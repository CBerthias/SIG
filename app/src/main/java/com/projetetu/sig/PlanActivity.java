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
    private String fileName = "map.html";
    private String originalContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        webView = findViewById(R.id.simpleWebView);
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
        chargePage("etage_un");
    }

    private void chargePage(String couche) {
        String content = originalContent.replace("%COUCHE%", couche);
        webView.loadData(content, "text/html", "utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
    }

    public void switchLayer(View view) {
        SwitchCompat switchEtage = findViewById(R.id.switchEtage);
        if (switchEtage.isChecked()) {
            chargePage("etage_deux");
            switchEtage.setText("Etage 2");
        } else {
            chargePage("etage_un");
            switchEtage.setText("Etage 1");
        }
    }
}