package com.example.joe.stopwatch.activities;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joe.stopwatch.R;
import com.example.joe.stopwatch.util.Achievement;

/**
 * 
 */
public class acheivementDetailsActivity extends AppCompatActivity {

    /** The achievement to display details about**/
    private Achievement selectedAchievement;
    /** The imageview to display the selectedAchievement's image **/
    private ImageView image;
    /** The textview to display the name of the selected Achievemnt**/
    private TextView name;
    /** The textview to display the description of the selectedAChievement **/
    private TextView description;

    /** Called when this activity is created**/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acheivement_details);

        // Init fields
        initSelectedAchievement();
        image = (ImageView) findViewById(R.id.AchievementNameDetailImage);
        name = (TextView) findViewById(R.id.AchievementNameDetailText);
        description = (TextView) findViewById(R.id.AchievementDescriptionText);

        // populate fields
        image.setImageResource(selectedAchievement.getImage());
        String nameStr = selectedAchievement.getName();
        name.setText(nameStr);
        String descriptionStr = selectedAchievement.getDescription();
        description.setText(descriptionStr);
    }

    /** Initializes the selected achievement from the intent**/
    private void initSelectedAchievement() {
        Intent intent = getIntent();
        selectedAchievement = (Achievement) intent.getParcelableExtra("EXTRA_ACH");
        selectedAchievement.setContext(this);
    }

}
