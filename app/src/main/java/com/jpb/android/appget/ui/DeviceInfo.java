package com.jpb.android.appget.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.jpb.android.appget.R;

public class DeviceInfo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_device_info);
        TextView title = findViewById(R.id.text3);
        TextView desc = findViewById(R.id.text4);
        TextView title2 = findViewById(R.id.text5);
        TextView desc2 = findViewById(R.id.text6);
        MaterialCardView card = findViewById(R.id.card1);
        MaterialCardView card2 = findViewById(R.id.card2);
        title.setText("Manufacturer/OEM");
        desc.setText(Build.MANUFACTURER);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        title2.setText("Device");
        desc2.setText(Build.MODEL);
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextView title3 = findViewById(R.id.text7);
        TextView desc3 = findViewById(R.id.text8);
        TextView title4 = findViewById(R.id.text9);
        TextView desc4 = findViewById(R.id.text10);
        MaterialCardView card3 = findViewById(R.id.card3);
        MaterialCardView card4 = findViewById(R.id.card4);
        title3.setText("Android version");
        desc3.setText(Build.VERSION.RELEASE);
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        title4.setText("Android Security Patch");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            desc4.setText(Build.VERSION.SECURITY_PATCH);
        } else {
            desc4.setText("not available");
        }
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextView title5 = findViewById(R.id.text11);
        TextView desc5 = findViewById(R.id.text12);
        TextView title6 = findViewById(R.id.text13);
        TextView desc6 = findViewById(R.id.text14);
        TextView title7 = findViewById(R.id.text15);
        TextView desc7 = findViewById(R.id.text16);
        MaterialCardView card5 = findViewById(R.id.card5);
        MaterialCardView card6 = findViewById(R.id.card6);
        MaterialCardView card7 = findViewById(R.id.card7);
        title5.setText("Build Tag");
        desc5.setText(Build.DISPLAY);
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Configuration config = this.getResources().getConfiguration();
        title6.setText("Smallest screen width");
        desc6.setText(String.valueOf(config.smallestScreenWidthDp) + "dp");
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        title7.setText("Build Fingerprint");
        desc7.setText(Build.FINGERPRINT);
        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}