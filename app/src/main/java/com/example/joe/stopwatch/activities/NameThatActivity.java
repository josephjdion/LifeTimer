package com.example.joe.stopwatch.activities;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.CursorAdapter;
        import android.widget.EditText;
        import android.widget.Spinner;

        import com.example.joe.stopwatch.R;
        import com.example.joe.stopwatch.util.TimedActivity;
        import com.example.joe.stopwatch.sql.UISQLHelper;

public class NameThatActivity extends AppCompatActivity {
    private TimedActivity newActivity;
    private String ActivityName;
    /** If false, custom input will be displayed */
    private  boolean activitySpinnerIsActive = true;

    //SQL Fields
    private UISQLHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_that);
        ActivityName = null;
        Spinner nameSpinner = (Spinner) findViewById(R.id.ActivityNameSpinner);
        Spinner styleSpinner = (Spinner) findViewById(R.id.timingStyleSpinner);
        sqlHelper = new UISQLHelper(this);
        populateNameSpinner(nameSpinner);
        populateStyleSpinner(styleSpinner);
        // NameSpinner is displayed by default
        toggleTextInput(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqlHelper.endHelper();
    }

    private void populateNameSpinner(Spinner spinner) {
        CursorAdapter listAdapter= sqlHelper.getCursorAdapterFromSQL();
        spinner.setAdapter(listAdapter);
    }

    private void populateStyleSpinner(Spinner spinner) {
        // old array way
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.timingTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void changeLayout(View view) {
        switchInput();
    }

    private void switchInput() {
        if (activitySpinnerIsActive) {
            toggleTextInput(true);
            activitySpinnerIsActive=false;
        } else {
            toggleTextInput(false);
            activitySpinnerIsActive=true;
        }
    }

    private void toggleTextInput(boolean isVisible) {
        toggleSpinnerInput(!isVisible);
        EditText et = (EditText) findViewById(R.id.ActivityNameField);
        Button but = (Button) findViewById(R.id.cancelButton);
        int visibility = View.GONE;
        if(isVisible==true)
            visibility = View.VISIBLE;
        but.setVisibility(visibility);
        et.setVisibility(visibility);
    }

    private void toggleSpinnerInput(Boolean isVisible) {
        Spinner spinner = (Spinner) findViewById(R.id.ActivityNameSpinner);
        Button but = (Button) findViewById(R.id.otherButton);
        int visibility = View.GONE;
        if(isVisible==true)
            visibility = View.VISIBLE;
        spinner.setVisibility(visibility);
        but.setVisibility(visibility);
    }

    public void startTimerActivity(View view) {
        EditText messageView = (EditText) findViewById(R.id.ActivityNameField);
        String fieldActivityName = messageView.getText().toString();
        Spinner mySpin = (Spinner) findViewById(R.id.ActivityNameSpinner);
        String spinnerActivityName = sqlHelper.getActName();

        ActivityName = fieldActivityName;
        if (ActivityName.equals("Activity Name")) {
            ActivityName = spinnerActivityName;
        }

        Log.d("Passing", "I am going to pass: " + "/" +ActivityName +"/");

        // create the intent since it is now null
         Intent intent = new Intent(NameThatActivity.this, StopwatchActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, ActivityName);
        startActivity(intent);
    }
}