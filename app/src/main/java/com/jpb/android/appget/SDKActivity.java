package com.jpb.android.appget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class SDKActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdk);
        TextView title = findViewById(R.id.text17);
        TextView desc = findViewById(R.id.text18);
        TextView title2 = findViewById(R.id.text19);
        TextView desc2 = findViewById(R.id.text20);
        MaterialCardView card = findViewById(R.id.card8);
        MaterialCardView card2 = findViewById(R.id.card9);
        title.setText("Gradle version");
        desc.setText("8.6 RC1");
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        title2.setText("Android Gradle Plugin version");
        desc2.setText("8.2.0");
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextView title3 = findViewById(R.id.text21);
        TextView desc3 = findViewById(R.id.text22);
        TextView title4 = findViewById(R.id.text23);
        TextView desc4 = findViewById(R.id.text24);
        MaterialCardView card3 = findViewById(R.id.card10);
        MaterialCardView card4 = findViewById(R.id.card11);
        title3.setText("Jetpack Compose version");
        desc3.setText("1.6.0 beta 3");
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        title4.setText("Kotlin version");
        desc4.setText("1.9.10");
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextView title5 = findViewById(R.id.text25);
        TextView desc5 = findViewById(R.id.text26);
        TextView title6 = findViewById(R.id.text27);
        TextView desc6 = findViewById(R.id.text28);
        MaterialCardView card5 = findViewById(R.id.card12);
        MaterialCardView card6 = findViewById(R.id.card13);
        title5.setText("Target SDK/Compile SDK");
        desc5.setText("34 (Android 14, Upside Down Cake)");
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        title6.setText("Min SDK");
        desc6.setText("22 (Android 5.1 Lollipop)");
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}