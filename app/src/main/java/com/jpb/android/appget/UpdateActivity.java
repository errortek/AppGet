package com.jpb.android.appget;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
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

        String your_apppackagename = "com.jpb.scratchtappy";
        PackageManager packageManager = getPackageManager();
        ApplicationInfo applicationInfo = null;
        int versionCode;
        versionCode = 1;
        try {
            applicationInfo = packageManager.getApplicationInfo(your_apppackagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (applicationInfo == null) {
            // not installed it will open your app directly on playstore
            versionCode = 1;
        } else {
            // Installed
            PackageInfo AppInfo = null;
            try {
                AppInfo = packageManager.getPackageInfo(your_apppackagename, 0);
            } catch (PackageManager.NameNotFoundException e) {
                //throw new RuntimeException(e);
            }
            versionCode = AppInfo.versionCode;
            if (versionCode > onlineVersionCode) {
                cardView.setVisibility(GONE);
            }
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
                                TextView text = (TextView) findViewById(R.id.textView24);
                                text.setText( "e ->" + bo.toString());
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

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }
}