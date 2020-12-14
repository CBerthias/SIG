package com.projetetu.sig;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PieceActivity extends AppCompatActivity {

    TextView placeId;
    EditText placeFonction;
    TextView placeEtage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece);
        Intent myIntent = getIntent();
        placeId = (TextView) findViewById(R.id.placeID);
        placeFonction = (EditText) findViewById(R.id.placeFonction);
        placeEtage = (TextView) findViewById(R.id.placeEtage);

        placeId.setText(myIntent.getStringExtra("id"));
        placeFonction.setText(myIntent.getStringExtra("fonction"), TextView.BufferType.EDITABLE);
        placeEtage.setText(myIntent.getStringExtra("etage"));

    }

    public void setFonction(View view) {
        placeFonction = (EditText) findViewById(R.id.placeFonction);
        String etage = placeEtage.getText().toString();
        String id = placeId.getText().toString();
        String new_fonction = placeFonction.getText().toString();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        finish();
        /*
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver O.K.");
            String url = "jdbc:postgresql://192.168.1.62:5432/toto";
            String user = "postgres";
            String passwd = "mysecretpassword";
            Connection conn = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connexion effective !");
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }
}