package com.jpb.android.appget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class STActivity2 extends AppCompatActivity {

    String TextFileURL = "https://github.com/jpbandroid/AppGet-Resources/raw/main/USP/updated_date.txt" ;
    String TextFileURL2 = "https://github.com/jpbandroid/AppGet-Resources/raw/main/USP/version.txt" ;
    String TextFileURL3 = "https://github.com/jpbandroid/AppGet-Resources/raw/main/USP/description.txt" ;
    String TextFileURL4 = "https://github.com/jpbandroid/AppGet-Resources/raw/main/USP/changelog.txt" ;
    TextView textView10;
    TextView textView11;
    TextView textView14;
    TextView textView16;
    Toolbar toolbar;
    Button installbut;
    URL url ;
    String TextHolder = "" , TextHolder2 = "", TextHolder3 = "", TextHolder4 = "", TextHolder5 = "";
    BufferedReader bufferReader ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stactivity2);
        textView10 = this.findViewById(R.id.textView10);
        textView11 = this.findViewById(R.id.textView11);
        textView14 = this.findViewById(R.id.textView14);
        textView16 = this.findViewById(R.id.textView16);
        toolbar = this.findViewById(R.id.toolbar);
        installbut = this.findViewById(R.id.button2);
        installbut.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                DownloadManager downloadmanager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse("https://github.com/jpbandroid/AppGet-Resources/raw/main/USP/app-release.apk");

                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setTitle("Unified ScratchTappy Platform");
                request.setDescription("Downloading");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "USP.apk");
                downloadmanager.enqueue(request);
            };
        });
        setSupportActionBar(toolbar);

    }
}