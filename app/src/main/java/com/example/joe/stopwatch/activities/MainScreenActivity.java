package com.example.joe.stopwatch.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.joe.stopwatch.R;
import com.example.joe.stopwatch.sql.UISQLHelper;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Main", "MainScreen Has Started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


    }

    public void StartNewActivity(View view) {
        Intent intent = new Intent(this, NameThatActivity.class);
        startActivity(intent);
    }

   /* public void onClickCalendar(View view){
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }*/

    public void onClickDebugReset(View view) {
        UISQLHelper uisqlHelper = new UISQLHelper(this);
        uisqlHelper.reset();
        uisqlHelper.endHelper();
        uisqlHelper = new UISQLHelper(this);
        uisqlHelper.addSamplesAndIntialize();
    }
}
