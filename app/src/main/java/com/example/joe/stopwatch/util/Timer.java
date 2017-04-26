package com.example.joe.stopwatch.util;

import android.os.Handler;
import android.widget.TextView;

/**
 * Created by Joe on 4/21/17.
 */

public class Timer {
    /**This should only be -1 or 1*/
    private boolean running = false;
    private int deltaTime;
    private int displaySeconds;
    private TextView timeView;
    private int totalSecondsSpent = 0;

    public Timer(TextView timeView) {
        this.timeView = timeView;
        deltaTime = 1;
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void reset() {
        running = false;
        displaySeconds = 0;
        totalSecondsSpent = 0;
    }

    public void startTimerThread() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = displaySeconds / 3600;
                int minutes = (displaySeconds % 3600) / 60;
                int secs = displaySeconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (isInBounds() && running) {
                    displaySeconds += deltaTime;
                    totalSecondsSpent++;
                }
                // delay thread for one second
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void setIsRunning(boolean setRunning) {
        running = setRunning;
    }

    /**
     * This should always return true for a regular timer.
     * @return
     */
    private boolean isInBounds() {
        return true;
    }

    public int getTotalSecondsSpent() {return totalSecondsSpent;}
}
