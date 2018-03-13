package com.example.swain.myapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextClock;
import android.widget.TextView;

/**
 * Created by swain on 2/7/18.
 */

public class EggTimer extends AppCompatActivity{

    SeekBar mySeekBar;
    TextView myTextClock;
    Button myButton;
    CountDownTimer countDownTimer;
    int timer;
    boolean start = false;

    public void updateTimer(int seconds) {
        timer = seconds;
        int minute = seconds / 60;
        int sec = seconds - minute * 60;
        if(sec < 10) {
            myTextClock.setText(minute + ":0" + sec);
        } else {
            myTextClock.setText(minute + ":" + sec);
        }
    }

    public void resetTimer() {
        mySeekBar.setEnabled(true);
        myButton.setText("Go");
        countDownTimer.cancel();
        start = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);

        mySeekBar = findViewById(R.id.seekBar);
        myTextClock = findViewById(R.id.myTextClock);
        myButton = findViewById(R.id.startStop);

        mySeekBar.setMax(300);
        mySeekBar.setProgress(0);
        myTextClock.setText("00:00");

        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void startStop(View view) {
        if(start == false) {
            mySeekBar.setEnabled(false);
            start = true;
            myButton.setText("Stop");
            countDownTimer = new CountDownTimer(timer * 1000 + 100, 1000) {
                public void onTick(long millisUntilFinish) {
                    updateTimer((int)(millisUntilFinish / 1000));
                    Log.i("Timer: " , (millisUntilFinish / 1000) + "");
                }

                public void onFinish() {
                    updateTimer(0);
                    resetTimer();
                }
            };
            countDownTimer.start();

        } else {
            resetTimer();
        }
    }
}
