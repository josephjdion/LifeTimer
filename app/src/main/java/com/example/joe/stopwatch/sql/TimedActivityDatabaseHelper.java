package com.example.joe.stopwatch.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.joe.stopwatch.util.TimedActivity;
import com.example.joe.stopwatch.widgets.materialcalendar.Util.CalendarDay;
/**
 * Created by Joe on 4/20/17.
 */

public class TimedActivityDatabaseHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "activities";
    private static int DB_VERSION = 1;

    public TimedActivityDatabaseHelper(Context context) {
        super(context, DB_NAME,null , DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("SQL", "Still using onCreate() sample values");
        initialCreateSQL(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("SQL", "onUpgrade() has been called. This should not of happened");
    }

    private void initialCreateSQL(SQLiteDatabase db) {
        // Create the first table
        db.execSQL("CREATE TABLE "+SQL_NAME+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ACT_NAME TEXT, " +
                "MINUTES_SPENT INTEGER, " +
                "DATE TEXT);");
        loadSampleActivities(db);
    }

    private void loadSampleActivities(SQLiteDatabase db) {
        CalendarDay date1 = new CalendarDay(4, 5, 2017);
        Log.d("Date", date1.toString());
        CalendarDay date2 = new CalendarDay(4, 6, 2017);
        Log.d("Date", date2.toString());
        CalendarDay date3 = new CalendarDay(4, 7, 2017);
        Log.d("Date", date3.toString());

        insertAct(db, "TestAct", 30, date1);
        insertAct(db, "TestAct", 25, date2);
        insertAct(db, "OtherTestAct", 20, date3);
        Log.d("SQL", "This loadSampleActivities has executed");
    }

    public void insertAct(SQLiteDatabase db, String actName, int minutes,
                                   CalendarDay date) {
        ContentValues actValues = new ContentValues();
        actValues.put("ACT_NAME", actName);
        actValues.put("MINUTES_SPENT", minutes);
        actValues.put("DATE", date.toString());
        db.insert(SQL_NAME, null, actValues);
    }

    public void printOutAllTheColumnNames(SQLiteDatabase db) {
        Cursor dbCursor = db.query(getSQLName(), null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        for (String str: columnNames)
            Log.d("SQL", "ColumnNAME: " + str);
    }

    public void resetTable(SQLiteDatabase db) {
        db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+ SQL_NAME);
        onCreate(db);
    }

    private static final String SQL_NAME = "TIMEDACTS";

    public static String getSQLName(){return SQL_NAME;}
}