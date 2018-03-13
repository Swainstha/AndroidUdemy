package com.example.swain.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

/**
 * Created by swain on 2/8/18.
 */
//http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22
public class WeatherApp extends AppCompatActivity{

    TextView weatherTextView;
    EditText locationEditText;
    Button viewWeather;
    DownloadWeatherData downloadWeatherData;

    public class DownloadWeatherData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream input = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(input);  //be able to read the input stream
                int data = reader.read();
                String result = "";
                while(data != -1) {

                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;

            } catch(MalformedURLException m) {
                m.printStackTrace();

            } catch(IOException e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("Content", result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                String weatherInfo = jsonObject.getString("weather");
                Log.i("Weather: ", weatherInfo);

                JSONArray arr = new JSONArray(weatherInfo);
                for(int i =0;i < arr.length();i++) {
                    JSONObject data = arr.getJSONObject(i);

                    weatherTextView.setText("main: " + data.getString("main") + "\ndescription: " + data.getString("description"));
                    Log.i("main", data.getString("main"));
                    Log.i("description", data.getString("description"));
                    
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_app);

        weatherTextView = findViewById(R.id.weatherTextView);
        locationEditText = findViewById(R.id.locationEditText);
        viewWeather = findViewById(R.id.viewWeather);
        downloadWeatherData = new DownloadWeatherData();

    }

    public void viewWeather(View view) {

        String location = locationEditText.getText().toString();

        try {
            new DownloadWeatherData().execute("http://openweathermap.org/data/2.5/weather?q=" + location + "&appid=b6907d289e10d714a6e88b30761fae22").get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
