package com.example.joe.stopwatch.widgets.materialcalendar.Interface;

import com.example.joe.stopwatch.widgets.materialcalendar.DayView;
import com.example.joe.stopwatch.widgets.materialcalendar.Util.CalendarDay;

public interface DayViewDecorator {
    boolean shouldDecorate(CalendarDay day);

    void decorate(DayView view);
}
