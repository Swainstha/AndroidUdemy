package com.example.swain.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * Created by swain on 2/12/18.
 */

public class Notes extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.swain.myapplication";

    SharedPreferences sharedPreferences;
    ListView noteListView;
    ArrayAdapter<String> noteArrayAdapter;
    static ArrayList<String> noteArrayList;
    int count = 0;
    int currentCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);

        noteListView = findViewById(R.id.noteListView);
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("Item Number", i + "");
                currentCount = i + 1;
                Intent intent = new Intent(Notes.this, CreateNote.class);
                intent.putExtra(EXTRA_MESSAGE, Integer.toString(i+1));
                startActivityForResult(intent, 2);

            }
        });

        sharedPreferences = this.getSharedPreferences("com.example.swain.myapplication", Context.MODE_PRIVATE);
//        sharedPreferences.edit().clear().apply();
        Log.i("count",sharedPreferences.getInt("count",0) + "");
        if(sharedPreferences.getInt("count",0) == 0) {
            sharedPreferences.edit().putInt("count",1).apply();
            sharedPreferences.edit().putString("1","hello").apply();
            count = sharedPreferences.getInt("count",0);
            Log.i("on create","if");

            display();
        } else {
            count = sharedPreferences.getInt("count",0);
            Log.i("on create","else");
            display();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.add_notes,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.add_note) {
            Intent intent = new Intent(this, CreateNote.class);
            startActivityForResult(intent, 1);
            return true;
        }

        return false;
    }

    public void display() {
        int getCount = sharedPreferences.getInt("count",0);
        noteArrayList = new ArrayList<>();
        for(int i = 1; i <= getCount;i++) {
            noteArrayList.add(sharedPreferences.getString(Integer.toString(i), ""));
        }

        noteArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteArrayList);
        noteListView.setAdapter(noteArrayAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                count++;

                String returnValue = data.getStringExtra("data");
                sharedPreferences.edit().putInt("count",count).apply();
                sharedPreferences.edit().putString(Integer.toString(count),returnValue).apply();
                Log.i("On Activity Result Work",returnValue + "  " + count);
                display();
            } else {
                Log.i("On Activity Result","request was unsuccessful");
            }
        } else if(requestCode == 2){
            if(resultCode == RESULT_OK) {
                String returnValue = data.getStringExtra("data");
                sharedPreferences.edit().putString(Integer.toString(currentCount),returnValue).apply();
                Log.i("On Activity Result Work",returnValue + "  " + currentCount);
                display();
            }

        }
    }
}
