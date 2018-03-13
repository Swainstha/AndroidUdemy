package com.example.swain.myapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by swain on 2/7/18.
 */

public class BrainTrainer extends AppCompatActivity{

    Button startButton;
    Button button[] = new Button[4];
    TextView timerTextView, scoreTextView, resultTextView, problemTextView;
    CountDownTimer countDownTimer;
    GridLayout layout;
    int ansButton = 1;
    int numberOfQuestions = 0;
    int numberOfCorrectAnswers = 0;

    public void startTimer() {
        countDownTimer = new CountDownTimer(10000 + 100, 1000) {
            @Override
            public void onTick(long l) {
                displayTimer(l);
            }

            @Override
            public void onFinish() {
                displayTimer(0);
                resultTextView.setText("Time Up");
                numberOfQuestions++;
                updateScore();
                askQuestion();
                startTimer();
            }
        };
        countDownTimer.start();
    }

    public void displayTimer(long millis) {
        timerTextView.setText((millis / 1000) + "s");
    }

    public void chooseAnswer(View view) {
        countDownTimer.cancel();
        String tag =  (String)view.getTag();
        Log.i("Tag", tag + "");
        if(Integer.valueOf(tag)== (ansButton + 1)) {
            resultTextView.setText("Correct");
            numberOfCorrectAnswers++;
        } else {
            resultTextView.setText("Wrong");
        }
        numberOfQuestions++;
        updateScore();
        askQuestion();
        startTimer();
    }

    public void updateScore() {
        scoreTextView.setText(numberOfCorrectAnswers + "/" + numberOfQuestions);
    }
    public void askQuestion() {
        Random rand = new Random();
        int one = rand.nextInt(150) + 50;
        int two = rand.nextInt(25) + 1;
        ansButton = rand.nextInt(3) + 1;
        int sign = rand.nextInt(4) + 1;
        int ans = 0;
        int fourAns[] = new int[4];
        for(int i = 0; i < 4;i++) {
            fourAns[i]= rand.nextInt(100) + 1;
        }
        switch(sign) {
            case 1:
                ans = one + two;
                problemTextView.setText(one + " + " + two);
                break;
            case 2:
                ans = one - two;
                problemTextView.setText(one + " - " + two);
                break;
            case 3:
                one = rand.nextInt(20) + 1;
                two = rand.nextInt(30) + 1;
                ans = one * two;
                problemTextView.setText(one + " X " + two);
                break;
            case 4:
                ans = one / two;
                problemTextView.setText(one + " / " + two);
                break;
        }
        fourAns[ansButton] = ans;
        Log.i("Four answer" , fourAns[3] + "");
        for(int i = 0;i< 4;i++) {
            button[i].setText(fourAns[i] + "");
        }
    }

    public void startQuestion(View view) {
        startButton.setVisibility(view.INVISIBLE);
        layout.setVisibility(view.VISIBLE);
        timerTextView.setVisibility(view.VISIBLE);
        scoreTextView.setVisibility(view.VISIBLE);
        resultTextView.setVisibility(view.VISIBLE);
        problemTextView.setVisibility(view.VISIBLE);
        askQuestion();
        startTimer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brain_trainer);

        startButton = findViewById(R.id.startButton);
        button[0] = findViewById(R.id.button1);
        button[1] = findViewById(R.id.button2);
        button[2] = findViewById(R.id.button3);
        button[3] = findViewById(R.id.button4);

        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        resultTextView = findViewById(R.id.resultTextView);
        problemTextView = findViewById(R.id.problemTextView);

        layout = findViewById(R.id.gridLayout);

    }
}
