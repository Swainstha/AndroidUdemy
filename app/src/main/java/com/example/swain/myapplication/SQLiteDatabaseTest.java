package com.example.swain.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by swain on 2/13/18.
 */

public class SQLiteDatabaseTest extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brain_trainer);

        try{
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Users", Context.MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers(name VARCHAR, age INTEGER(3), id INTEGER PRIMARY KEY AUTOINCREMENT)");
//            sqLiteDatabase.execSQL("DELETE FROM newUsers ");
//            sqLiteDatabase.execSQL("DROP TABLE newUsers");
////            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR, age INT(3))");
//            sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES('Swain',22)");
//            sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES('Eshika',17)");
//            sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES('Prakriti',16)");
//            sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES('Swikriti',11)");

//            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Events", Context.MODE_PRIVATE, null);
//            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS event(name VARCHAR, year INT(4))");
//            sqLiteDatabase.execSQL("INSERT INTO event (name, year) VALUES('Born',1995)");
//            sqLiteDatabase.execSQL("INSERT INTO event (name, year) VALUES('Pass',2017)");

            sqLiteDatabase.execSQL("DELETE FROM newUsers WHERE id = 1");

//
//            sqLiteDatabase.execSQL("UPDATE users SET age = 15 WHERE name = 'Eshika'");
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM newUsers", null);


            int nameIndex = c.getColumnIndex("name");
//            int yearIndex = c.getColumnIndex("year");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");
            c.moveToFirst();
            while(c != null) {
                Log.i("ID", Integer.toString(c.getInt(idIndex)));
                Log.i("name", c.getString(nameIndex));
                Log.i("age", Integer.toString(c.getInt(ageIndex)));
                c.moveToNext();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
