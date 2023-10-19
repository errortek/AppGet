package com.jpb.android.appget.ui;

import androidx.appcompat.app.AppCompatActivity;

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
        MaterialCardView card = findViewById(R.id.card1);
        title.setText("Manufacturer/OEM");
        desc.setText(Build.MANUFACTURER);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}