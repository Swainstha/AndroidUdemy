package com.example.swain.myapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by swain on 2/7/18.
 */

public class Timer extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);

        new CountDownTimer(10000, 1000) {
            public void onTick(long millisecondsUntilFinish) {
                Log.i("Time left ",String.valueOf(millisecondsUntilFinish / 10));
            }

            public void onFinish() {
                Log.i("Oh yeah "," Countdown is finished!");
            }
        }.start();
//        final Handler handler = new Handler();
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//                // Insert the code here
//                Log.i("INFO", " one second has passed");
//                handler.postDelayed(this, 2000);
//
//            }
//        };
//        handler.post(run);
    }
}
