package com.example.swain.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by swain on 2/12/18.
 */

public class CreateNote extends AppCompatActivity {

    EditText noteEditText;
    Intent resultIntent;
    SharedPreferences sharedPreferences;
//    boolean newItem = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note);

        noteEditText = findViewById(R.id.noteEditText);
        sharedPreferences =  this.getSharedPreferences("com.example.swain.myapplication",Context.MODE_PRIVATE);
        Intent intent = getIntent();

        if(intent.getStringExtra("com.example.swain.myapplication") != null){
            String description = sharedPreferences.getString(intent.getStringExtra("com.example.swain.myapplication"),"");
            Log.i("data",description);
            noteEditText.setText(description);
        }

        resultIntent = new Intent();
        noteEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String note = noteEditText.getText().toString();
                resultIntent.putExtra("data", note);
                setResult(CreateNote.RESULT_OK, resultIntent);
                Log.i("On Text Changed", note);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

}
