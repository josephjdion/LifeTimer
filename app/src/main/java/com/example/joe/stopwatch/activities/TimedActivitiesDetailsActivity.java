package com.example.joe.stopwatch.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.joe.stopwatch.R;
import com.example.joe.stopwatch.util.TimedActivity;
import com.example.joe.stopwatch.sql.UISQLHelper;

public class TimedActivitiesDetailsActivity extends AppCompatActivity {

    private TimedActivity activityToDisplay;
    private UISQLHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TimedDetails", "I have been created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timed_activities_details);
        sqlHelper = new UISQLHelper(this);
        setActivityToDisplay();
        setAllText();
    }

    public void onClickEnd(View view) {
        saveActivityToDisplay();
        goToMainScreen();
    }

    private void goToMainScreen() {
        sqlHelper.endHelper();
        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
    }

    private void setActivityToDisplay() {
        Log.d("TimedDetails", "I am loading the intent");
        Intent intent = getIntent();
        this.activityToDisplay = (TimedActivity) intent.getParcelableExtra("EXTRA_ACT");
        Log.d("TimedDetails", "I have finished loading the intent");

    }

    private void saveActivityToDisplay() {
        if(sqlHelper!=null)
            Log.d("MyApp", "SQLHELPER isnt null");
        TimedActivity act = activityToDisplay;
        if(act != null)
            Log.d("MyApp", "The act isnt null");
        sqlHelper.saveActivity(act);
    }

    private void setAllText() {
        TextView nameText = (TextView) findViewById(R.id.actName);
        TextView dateText = (TextView) findViewById(R.id.actDateDetail);
        TextView timeText = (TextView) findViewById(R.id.actTimeDetail);
        nameText.setText(activityToDisplay.getActivityName());
        dateText.setText(activityToDisplay.getDate().toString());
        timeText.setText(activityToDisplay.getMinutesSpent() + " Minutes Spent");
    }

    private void saveNewActity() {

    }




}