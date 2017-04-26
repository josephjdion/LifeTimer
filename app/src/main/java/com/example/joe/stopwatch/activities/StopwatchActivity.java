package com.example.joe.stopwatch.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.joe.stopwatch.R;
import com.example.joe.stopwatch.util.TimedActivity;
import com.example.joe.stopwatch.util.Timer;

public class StopwatchActivity extends AppCompatActivity {

    private int seconds = 0;
    //private boolean running;
    private boolean wasRunning;
    private TimedActivity act;
    private Timer myTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        myTimer = new Timer((TextView) findViewById(R.id.time_view));
        // check if the program has had a save state
        if(savedInstanceState!=null){
            seconds = savedInstanceState.getInt("seconds");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        } else {
            act = new TimedActivity(getTextfromIntent());
        }
        myTimer.setIsRunning(false);
        myTimer.startTimerThread();
        setNameText();
    }

    private String getTextfromIntent() {
        Intent intent = getIntent();
        String messageText = intent.getStringExtra(Intent.EXTRA_TEXT);
        return messageText;
    }


    // only run after creating TimedActivity
    private void setNameText() {
        String messageText = getTextfromIntent();
        Log.d("MyApp", "Here is the name: "+messageText);
        TextView messageView = (TextView)findViewById(R.id.ActivityNameView);
        messageView.setText(messageText);
    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    protected void onStart()
    {
        super.onStart();
        if(wasRunning)
            myTimer.setIsRunning(true);
    }

    public void onClickStart(View view) {
        myTimer.start();
    }

    public void onClickStop(View view) {
        myTimer.stop();
    }

    public void onClickReset(View view) {
        myTimer.reset();
    }

    public void onClickEndCurrentActivity(View view) {
        myTimer.stop();
        int secondsSpent = myTimer.getTotalSecondsSpent();
        act.setEndTime(secondsSpent);
        goToStopScreen();
    }

    private void goToStopScreen() {
        Intent intent = new Intent(StopwatchActivity.this, TimedActivitiesDetailsActivity.class);
        //intent.setType("text/plain");
        intent.putExtra("EXTRA_ACT", this.act);
        startActivity(intent);
    }



}
