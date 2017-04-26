package com.example.joe.stopwatch.widgets.materialcalendar.Interface;

import com.example.joe.stopwatch.widgets.materialcalendar.Util.CalendarDay;

import java.util.ArrayList;
import java.util.Date;

public interface CalendarCallback {
    Date getDateSelected();

    ArrayList<CalendarDay> getEvents();

    boolean getIndicatorsVisible();
}
