package com.example.swain.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by swain on 2/13/18.
 */

public class HackANews extends AppCompatActivity {

    ArrayAdapter<String> newsArrayAdapter;
    ArrayList<String> titles;
    ArrayList<String> content;
    SQLiteDatabase articlesDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hack_a_news);

        titles = new ArrayList<>();
        content = new ArrayList<>();
        ListView newsListView = findViewById(R.id.newsListView);
        newsArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,titles);
        newsListView.setAdapter(newsArrayAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),ArticleActivity.class);
                intent.putExtra("content",content.get(i));
                startActivity(intent);
            }
        });

        articlesDB = this.openOrCreateDatabase("Articles",MODE_PRIVATE, null);
//        articlesDB.execSQL("DROP TABLE articles");
        articlesDB.execSQL("CREATE TABLE IF NOT EXISTS articles (id INTEGER PRIMARY KEY AUTOINCREMENT, articleId INTEGER, title VARCHAR, content VARCHAR)");

        updateListView();

        DownloadTask task = new DownloadTask();
        try {
            task.execute(" https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void updateListView() {
        Cursor c = articlesDB.rawQuery("SELECT * FROM articles",null);
        int contentIndex = c.getColumnIndex("content");
        int titleIndex = c.getColumnIndex("title");
        try {
            if (c.moveToFirst()) {
                titles.clear();
                content.clear();

                do {
                    content.add(c.getString(contentIndex));
                    titles.add(c.getString(titleIndex));
                    c.moveToNext();
                } while (c != null);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        newsArrayAdapter.notifyDataSetChanged();
    }

    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(strings[0]);
                urlConnection  = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while(data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
//                Log.i("Url content", result);

                JSONArray jsonArray = new JSONArray(result);
                int numOfItems = 5;
                if(jsonArray.length() < numOfItems) {
                    numOfItems = jsonArray.length();
                }
                for(int i = 1; i < numOfItems;i++) {
                    String articleID = jsonArray.getString(i);
                    url = new URL(" https://hacker-news.firebaseio.com/v0/item/" + articleID + ".json?print=pretty");
                    urlConnection  = (HttpURLConnection) url.openConnection();
                    in = urlConnection.getInputStream();
                    reader = new InputStreamReader(in);
                    data = reader.read();
                    String articleInfo = "";
                    while(data != -1) {
                        char current = (char) data;
                        articleInfo += current;
                        data = reader.read();
                    }
//                    Log.i("Article Info", articleInfo);
                    JSONObject jsonObject = new JSONObject(articleInfo);
                    if(!jsonObject.isNull("title")  && !jsonObject.isNull("url")) {
                        String articleTitle = jsonObject.getString("title");
                        String articleUrl = jsonObject.getString("url");
//                    Log.i("Title", articleTitle );
                        Log.i("Url", articleUrl);
                        url = new URL(articleUrl);
                        urlConnection = (HttpURLConnection) url.openConnection();
                        in = urlConnection.getInputStream();
                        reader = new InputStreamReader(in);
                        data = reader.read();
                        String articleContent = "";
                        while (data != -1) {
                            char current = (char) data;
                            articleContent += current;
                            data = reader.read();
                        }
                        String sql = "INSERT INTO articles(articleId, title, content) VALUES(?,?,?)";
                        SQLiteStatement statement = articlesDB.compileStatement(sql);
                        statement.bindString(1, articleID);
                        statement.bindString(2, articleTitle);
                        statement.bindString(3, articleContent);
                        statement.execute();
                        Log.i("Article Content", articleContent);
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            updateListView();
        }
    }
}
