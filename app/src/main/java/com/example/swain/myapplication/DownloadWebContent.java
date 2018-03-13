package com.example.swain.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by swain on 2/7/18.
 */

public class DownloadWebContent extends AppCompatActivity{

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection)url.openConnection(); //openning the connection with the url
                InputStream input = urlConnection.getInputStream(); //getting the input stream
                InputStreamReader reader = new InputStreamReader(input);  //be able to read the input stream
                int data = reader.read();
                while(data != -1) {

                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch(Exception e) {
                e.printStackTrace();
                return "Failed";
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_web_content);

        Log.i("Result", "hehehe");
        DownloadTask downloadTask = new DownloadTask();
        String result = null;

        try {
            result = downloadTask.execute("https://www.ecowebhosting.co.uk/").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.i("Result", result);
    }

    public void displayImage(View view) {
        Log.i("INFO", "button Pressed");
    }
}
