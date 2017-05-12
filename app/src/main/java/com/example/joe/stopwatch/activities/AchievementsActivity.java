package com.example.joe.stopwatch.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.joe.stopwatch.R;
import com.example.joe.stopwatch.util.Achievement;
import com.example.joe.stopwatch.util.Achievements;

public class AcheivementsActivity extends AppCompatActivity {
    GridView gridView;
    Context con = this;
    Achievements achievements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acheivements);

        // init
        gridView = (GridView) findViewById(R.id.gridView);
        achievements = new Achievements(this);
        populateGridView populator = new populateGridView();

        // populate
        populator.run();
    }

    private class populateGridView implements Runnable {

        @Override
        public void run() {
            gridView.setAdapter(new GridAdapter());
        }
    }



    public void gridItemClicked(int i) {
        goToDetails(achievements.getAchievement(i));
    }

    private void goToDetails(Achievement achievement) {
        Intent intent = new Intent(AcheivementsActivity.this,
                acheivementDetailsActivity.class);
        intent.putExtra("EXTRA_ACH", achievement);
        startActivity(intent);
    }


    private class GridAdapter extends BaseAdapter {
        private Context mContext = con;

        @Override
        public int getCount() {
            return achievements.getSize();
        }

        @Override
        public Object getItem(int i) {
            return achievements.getAchievement(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View gridTile;
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                gridTile = new View(mContext);
                gridTile = inflater.inflate(R.layout.grid_single, null);

                Achievement achievement = achievements.getAchievement(position);
                Log.d("DEBUG", "I am looking at the arraylist");
                TextView textView = (TextView) gridTile.findViewById(R.id.grid_text);
                ImageView imageView = (ImageView)gridTile.findViewById(R.id.grid_image);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AcheivementsActivity a = (AcheivementsActivity) mContext;
                        a.gridItemClicked(position);
                    }
                });
                Log.d("Debug", ""+achievement.getName());
                String strToUse= achievement.getName();
                textView.setText(strToUse);
                imageView.setImageResource(achievement.getImage());
            } else {
                gridTile = (View) convertView;
            }
            return gridTile;
        }
    }
}
