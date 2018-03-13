package com.example.swain.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    public void LogInClick(View view) {
//
//        EditText myUser = findViewById(R.id.myUser);
//        EditText myPass = findViewById(R.id.myPass);
//        Log.i("User Name",myUser.getText().toString());
//        Log.i("Password",myPass.getText().toString());
//
//        Toast.makeText(MainActivity.this,"Welcome " + myUser.getText().toString(),Toast.LENGTH_LONG).show();
//    }
//    public void changePic(View view) {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ImageView imageView = findViewById(R.id.myImage);
//                imageView.setImageResource(R.drawable.cat1);
//                Log.i("INFO","Image changed");
//            }
//        });
//        thread.run();
//    }

    public void convertUSToEuro(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ImageView imageView = findViewById(R.id.moneyPic);
                imageView.setImageResource(R.drawable.euro);
            }
        }).run();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EditText money = findViewById(R.id.enterCurrency);
                    double toEuro = Double.valueOf(money.getText().toString()) * 0.81;
                    Toast.makeText(MainActivity.this, String.format("%.2f",toEuro) + " Euro", Toast.LENGTH_LONG).show();
                } catch(Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage() , Toast.LENGTH_LONG).show();
                }
            }
        }).run();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
