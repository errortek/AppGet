package com.jpb.android.appget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UpdateActivity extends AppCompatActivity {

    private int onlineVersionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        readVersion();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        MaterialCardView cardView = findViewById(R.id.cardU);
        PackageManager packageManager = getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo("com.jpb.scratchtappy", 0);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Retrieve the version information
        String versionName = packageInfo.versionName;
        int versionCode = packageInfo.versionCode;
        if (versionCode == onlineVersionCode) {
            cardView.setVisibility(View.GONE);
        }
    }
    private void readVersion() {

        new Thread() {
            @Override
            public void run() {
                String path ="https://github.com/jpbandroid/AppGet-Resources/raw/main/USP/version_int.txt";
                URL u = null;
                try {
                    u = new URL(path);
                    HttpURLConnection c = (HttpURLConnection) u.openConnection();
                    c.setRequestMethod("GET");
                    c.connect();
                    InputStream in = c.getInputStream();
                    final ByteArrayOutputStream bo = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    in.read(buffer); // Read from Buffer.
                    bo.write(buffer); // Write Into Buffer.
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // Get the package manager instance
                            PackageManager packageManager = getPackageManager();

                            try {
                                // Get the package information
                                PackageInfo packageInfo = packageManager.getPackageInfo("com.jpb.scratchtappy", 0);

                                // Retrieve the version information
                                String versionName = packageInfo.versionName;
                                int versionCode = packageInfo.versionCode;
                                TextView text = (TextView) findViewById(R.id.textView24);
                                text.setText(versionName + "->" + bo.toString());
                                onlineVersionCode = Integer.valueOf(bo.size());


                            } catch (PackageManager.NameNotFoundException e) {
                                e.printStackTrace();
                            }
                            try {
                                bo.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}