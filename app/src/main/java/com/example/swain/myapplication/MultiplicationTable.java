package com.example.swain.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

/**
 * Created by swain on 2/7/18.
 */

public class MultiplicationTable extends AppCompatActivity {

    ListView myListView;

    public void display(int timesTable) {
        final ArrayList<String> tableContent = new ArrayList<>();

        ArrayAdapter<String> multiplicationTableAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tableContent);

        myListView.setAdapter(multiplicationTableAdapter);
        for(int j = 1; j <= 10; j++) {
            tableContent.add(Integer.toString(timesTable *  j));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplication_table);

        myListView = findViewById(R.id.myListView);
        SeekBar multiplicationSeekbar = findViewById(R.id.mySeekBar);

        multiplicationSeekbar.setMax(20);
        multiplicationSeekbar.setProgress(10);
        display(10);
        multiplicationSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int min = 1;
                int timesTable;

                if(i < min) {
                    timesTable = min;
                } else {
                    timesTable = i;
                }
                display(timesTable);
                Log.i("Time Table: ",Integer.toString(timesTable));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
