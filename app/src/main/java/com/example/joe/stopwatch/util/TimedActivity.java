package com.example.joe.stopwatch.util;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.joe.stopwatch.widgets.materialcalendar.Util.CalendarDay;

/**
 * Created by Joe on 4/7/17.
 */

public class TimedActivity implements Parcelable {

    private int secondsSpent;
    private String activityName;
    private CalendarDay StartDate;

    public TimedActivity(String nameToUse) {
        secondsSpent = 0;
        StartDate = new CalendarDay();
        activityName = nameToUse;
    }
    public TimedActivity(String name, int minutesSpent, CalendarDay date) {
        this.activityName = name;
        this.secondsSpent = minutesSpent*60;
        this.StartDate = date;
    }

    public int getHoursSpent() {return this.secondsSpent/3600;}
    public int getMinutesSpent() { return this.secondsSpent/60;}
    public int getSecondsSpent() { return this.secondsSpent;}
    public String getYear() {return ""+ StartDate.getYear();}

    public String getActivityName() {
        return activityName;
    }
    public CalendarDay getDate() {
        return StartDate;
    }

    public String getListViewDescription() {
        return String.format("You Spent %d minutes %s", getMinutesSpent(), getActivityName());
    }

    public String getListViewDescriptionLong() {
        return String.format("You Spent %d minutes %s on %s", getMinutesSpent(), getActivityName()
                , StartDate.toString());
    }

    @Override
    public String toString() {
        return getListViewDescription();
    }


    protected TimedActivity(Parcel in) {
        secondsSpent = in.readInt();
        activityName = in.readString();
        StartDate = new CalendarDay(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(secondsSpent);
        dest.writeString(activityName);
        dest.writeString(StartDate.toString());
    }

    @SuppressWarnings("unused")
    public static final Creator<TimedActivity> CREATOR = new Creator<TimedActivity>() {
        @Override
        public TimedActivity createFromParcel(Parcel in) {
            return new TimedActivity(in);
        }

        @Override
        public TimedActivity[] newArray(int size) {
            return new TimedActivity[size];
        }
    };

    public void setEndTime(int endTime) {
        this.secondsSpent = endTime;
    }
}