package com.jpb.android.appget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class STActivity2 extends AppCompatActivity {

    String TextFileURL = "https://github.com/jpbandroid/AppGet-Resources/raw/main/USP/updated_date.txt" ;
    TextView textView10;
    Button button ;
    Toolbar toolbar;
    URL url ;
    String TextHolder = "" , TextHolder2 = "";
    BufferedReader bufferReader ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stactivity2);
        textView10 = this.findViewById(R.id.textView11);
        toolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new GetUpdateDate().execute();
    }

    public class GetUpdateDate extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                url = new URL(TextFileURL);

                bufferReader = new BufferedReader(new InputStreamReader(url.openStream()));

                while ((TextHolder2 = bufferReader.readLine()) != null) {

                    TextHolder += TextHolder2;
                }
                bufferReader.close();

            } catch (MalformedURLException malformedURLException) {

                // TODO Auto-generated catch block
                malformedURLException.printStackTrace();
                TextHolder = malformedURLException.toString();

            } catch (IOException iOException) {

                // TODO Auto-generated catch block
                iOException.printStackTrace();

                TextHolder = iOException.toString();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void finalTextHolder) {

            textView10.setText("Updated: " + TextHolder);

            super.onPostExecute(finalTextHolder);
        }

    }
}