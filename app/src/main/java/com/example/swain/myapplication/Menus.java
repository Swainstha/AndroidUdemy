package com.example.swain.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by swain on 2/12/18.
 */

public class Menus extends AppCompatActivity {

    SharedPreferences sharedPreference;
    TextView myTextView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case R.id.english:
                Log.i("case","English");
                setLanguage("English");
                break;
            case R.id.spanish:
                Log.i("case","Spanish");
                setLanguage("Spanish");
                break;
            default:
                return false;
        }
        return true;
    }

    public void setLanguage(String language) {
        sharedPreference.edit().putString("language", language).apply();

        myTextView.setText(language);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preferences);

        sharedPreference = this.getSharedPreferences("com.example.swain.myapplication", Context.MODE_PRIVATE);
        myTextView = findViewById(R.id.lantextView);

        String language = sharedPreference.getString("language", "");
        if ( language == "") {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.cat1)
                    .setTitle("Choose a language")
                    .setMessage("Which language would you like?")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setLanguage("English");
                            Toast.makeText(Menus.this, "You chose English", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setLanguage("Spanish");
                            Toast.makeText(Menus.this, "You chose Spanish", Toast.LENGTH_SHORT).show();

                        }
                    })
                    .show();
        } else {
            myTextView.setText(language);
        }
    }
    
}
