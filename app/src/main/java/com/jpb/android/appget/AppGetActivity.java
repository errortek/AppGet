package com.jpb.android.appget;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AppGetActivity extends AppCompatActivity {

    String TextFileURL = "https://github.com/jpbandroid/AppGet-Resources/raw/main/AppGet/updated_date.txt" ;
    String TextFileURL2 = "https://github.com/jpbandroid/AppGet-Resources/raw/main/AppGet/version.txt" ;
    String TextFileURL3 = "https://github.com/jpbandroid/AppGet-Resources/raw/main/AppGet/description.txt" ;
    String TextFileURL4 = "https://github.com/jpbandroid/AppGet-Resources/raw/main/AppGet/changelog.txt" ;
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
                Uri uri = Uri.parse("https://github.com/jpbandroid/AppGet-Resources/raw/main/AppGet/app-release.apk");

                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setTitle("Unified ScratchTappy Platform");
                request.setDescription("Downloading");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "AppGet.apk");
                downloadmanager.enqueue(request);
            };
        });
        setSupportActionBar(toolbar);
        readUpdateDate();
        readVersion();
        readDescription();
        readChangelog();
    }

    private void readUpdateDate() {
        new Thread() {
            @Override
            public void run() {
                String path ="https://github.com/jpbandroid/AppGet-Resources/raw/main/AppGet/updated_date.txt";
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
                            TextView text = (TextView) findViewById(R.id.textView11);
                            text.setText("Updated: " + bo.toString());
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
    private void readVersion() {
        new Thread() {
            @Override
            public void run() {
                String path ="https://github.com/jpbandroid/AppGet-Resources/raw/main/AppGet/version.txt";
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
                            TextView text = (TextView) findViewById(R.id.textView10);
                            text.setText("Version: " + bo.toString());
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
    private void readDescription() {
        new Thread() {
            @Override
            public void run() {
                String path ="https://github.com/jpbandroid/AppGet-Resources/raw/main/AppGet/description.txt";
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
                            TextView text = (TextView) findViewById(R.id.textView14);
                            text.setText(bo.toString());
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
    private void readChangelog() {
        new Thread() {
            @Override
            public void run() {
                String path ="https://github.com/jpbandroid/AppGet-Resources/raw/main/AppGet/changelog.txt";
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
                            TextView text = (TextView) findViewById(R.id.textView16);
                            text.setText(bo.toString());
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
    private void readinfos() {
        new Thread() {
            @Override
            public void run() {
                String path ="https://github.com/jpbandroid/AppGet-Resources/raw/main/AppGet/changelog.txt";
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
                            TextView text = (TextView) findViewById(R.id.textView13);
                            text.setText(bo.toString());
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