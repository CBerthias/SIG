package com.projetetu.sig;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.Barcode;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.common.InputImage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivityDebug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goScan(View view) {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivityForResult(intent, 77);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 77) {
                Log.d(TAG, "onActivityResult: ICI");
                scanImage(data.getExtras().getParcelable("photo_result"));
            }
        }
    }

    private void scanImage(Uri uri) {
        BarcodeScannerOptions options =
                new BarcodeScannerOptions.Builder()
                        .setBarcodeFormats(
                                Barcode.FORMAT_QR_CODE)
                        .build();

        InputImage image = null;
        try {
            image = InputImage.fromFilePath(MainActivity.this, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert image != null;
        BarcodeScanner scanner = BarcodeScanning.getClient(options);

        scanner.process(image)
                .addOnSuccessListener(barcodes -> {
                    if (barcodes.size()==0)
                        Toast.makeText(this, "Aucun QR code trouvé", Toast.LENGTH_SHORT).show();
                    else {
                        Barcode premier = barcodes.get(0);
                        String value = premier.getRawValue();
                        String[] infos = value.split(",");
                        Intent intent = new Intent(this, PlanActivity.class);
                        intent.putExtra("etage", infos[0]);
                        intent.putExtra("x", infos[1]);
                        intent.putExtra("y", infos[2]);
                        intent.putExtra("zoom", infos[3]);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(e -> {
                    Log.d(TAG, "scanImage: Scan échoué");
                });
    }

    public void goPlan(View view) {

        Intent intent = new Intent(this, PlanActivity.class);
        intent.putExtra("etage", "etage_un");
        intent.putExtra("x", "11.5");
        intent.putExtra("y", "14.5");
        intent.putExtra("zoom", "21");
        startActivity(intent);
    }
}