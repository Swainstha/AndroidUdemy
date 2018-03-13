package com.example.swain.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by swain on 2/12/18.
 */

public class SharedPreference extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preferences);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.swain.myapplication", Context.MODE_PRIVATE);  //context is private so that only we can access data

        ArrayList<String> friends = new ArrayList<>();
        friends.add("Karun");
        friends.add("Binit");

        try {
            sharedPreferences.edit().putString("friends",ObjectSerializer.serialize(friends)).apply();
            //sharedPreferences.edit().putString("username","swain").apply();
            Log.i("username",friends.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> newFriends;
        try {
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ObjectSerializer.serialize(new ArrayList<String>()))); //if there are no "friends" then it will deserialize a serialized empty arraylist
            Log.i("username",newFriends.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String username = sharedPreferences.getString("username",""); // the s1 string is the default key if there is no username



    }
}
