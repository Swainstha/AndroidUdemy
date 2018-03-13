package com.example.swain.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by swain on 2/6/18.
 */

public class One extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two);

        ListView myListView = findViewById(R.id.myListView);
        final ArrayList<String> myFamily = new ArrayList<>();
        myFamily.add("Swayam Prakash SHrestha");
        myFamily.add("Induka Shrestha");
        myFamily.add("Swain Shrestha");
        myFamily.add("Eshika Shrestha");

//        ArrayList<String> myFamily1 = new ArrayList<String>(Arrays.asList("One","Two","Three", "Four"));

        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myFamily);
        myListView.setAdapter(myArrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("Person tapped: ",myFamily.get(i));
                Toast.makeText(One.this, myFamily.get(i) , Toast.LENGTH_LONG).show();

            }
        });
    }

    public void fade(View view) {

//        ImageView bartImageView = findViewById(R.id.bart);
//        ImageView homerImageView = findViewById(R.id.homer);
//
//        bartImageView.animate().alpha(0f).setDuration(3000);
//        homerImageView.animate().alpha(1f).setDuration(3000);


    }
}
