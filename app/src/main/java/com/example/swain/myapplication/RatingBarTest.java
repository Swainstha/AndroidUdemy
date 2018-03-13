package com.example.swain.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by swain on 2/13/18.
 */

public class RatingBarTest extends AppCompatActivity {

    RatingBar ratingBar;
    TextView rateTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_bar_test);

        ratingBar = findViewById(R.id.ratingBar);
        rateTextView = findViewById(R.id.rateTextView);

        rateTextView.setText(String.valueOf(ratingBar.getRating()));
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateTextView.setText(String.valueOf(v));
            }
        });

    }
}
