package com.example.joe.stopwatch.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.joe.stopwatch.util.TimedActivity;
import com.example.joe.stopwatch.widgets.materialcalendar.Util.CalendarDay;

import java.util.ArrayList;


/**
 * Created by Joe on 4/20/17.
 */

public class UISQLHelper {
    private Cursor cursor;
    private SQLiteDatabase db;
    private Context context;

    public UISQLHelper(Context context) {
        //mySQLHelper = new TimedActivityDatabaseHelper(context);
        this.context = context;
    }

    public CursorAdapter getCursorAdapterFromSQL() {
        CursorAdapter retCursorAdapter;
        try {
            SQLiteOpenHelper helper = new TimedActivityDatabaseHelper(context);
            String name = TimedActivityDatabaseHelper.getSQLName();
            db = helper.getReadableDatabase();
            // true tells SQL to get only distinct values
            //  null,null,"ACT_NAME",null,null,null tells what column to look for unique values
            cursor = db.query(true, name,
                    new String[]{"_id", "ACT_NAME"},
                    null,null,"ACT_NAME",null,null,null);
            retCursorAdapter = new SimpleCursorAdapter(context,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"ACT_NAME"},
                    new int[]{android.R.id.text1},
                    0);
            return retCursorAdapter;
        } catch (SQLiteException e)
        {
            Toast.makeText(context, "SQL EXCEPTION", Toast.LENGTH_SHORT).show();}
        Log.d("SQL", "SQL ERROR In UISQLHelper");
       return null;
    }

    public void saveActivity(TimedActivity actToSave) {
        TimedActivityDatabaseHelper mySQLHelper = new TimedActivityDatabaseHelper(context);
        db = mySQLHelper.getWritableDatabase();

        mySQLHelper.printOutAllTheColumnNames(db);
        String name = actToSave.getActivityName();
        Log.d("SaveDebug","Name: " + name);
        int  secondsSpent  = actToSave.getSecondsSpent();
        Log.d("SaveDebug","Time: " + secondsSpent);
        CalendarDay date = actToSave.getDate();
        Log.d("SaveDebug","Date: " + date.toString());
        mySQLHelper.insertAct(db, name, secondsSpent/60, date);
    }

    public String getActName() {return cursor.getString(1);}

    public void endHelper() {
        closeCursor();
        closeDB();
    }

    private void closeCursor() {
        if(cursor!=null)
            cursor.close();
    }


    private void closeDB() {
        if (db!=null)
            db.close();
    }

    public ArrayList<TimedActivity> getAllDatesArrayList() {
        SQLiteOpenHelper helper = new TimedActivityDatabaseHelper(context);
        db = helper.getReadableDatabase();

        Cursor cursor = db.query(TimedActivityDatabaseHelper.getSQLName(),
                new String[]{"_id", "ACT_NAME", "MINUTES_SPENT", "DATE"},
                null,null,null,null,null);

        ArrayList<TimedActivity> retArray = new ArrayList();

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                String name = cursor.getString(1);
                int minutesSpent = Integer.parseInt(cursor.getString(2));
                String dateString = cursor.getString(3);
                CalendarDay dateFromString = new CalendarDay(dateString);
                retArray.add(new TimedActivity(name, minutesSpent, dateFromString));
                cursor.moveToNext();
            }

        }
    return retArray;
    }


    public void reset() {
        TimedActivityDatabaseHelper helper = new TimedActivityDatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        helper.resetTable(db);
        db.close();
    }
    public void addSamplesAndIntialize() {
        TimedActivityDatabaseHelper sqlHelp = new TimedActivityDatabaseHelper(context);
    }
}
