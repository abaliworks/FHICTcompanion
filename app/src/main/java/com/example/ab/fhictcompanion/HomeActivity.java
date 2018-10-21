package com.example.ab.fhictcompanion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView mSchedule, mPeople, mGrades, mFontysNews, mAboutme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Linking
        mSchedule = (CardView) findViewById(R.id.schedule);
        mPeople = (CardView) findViewById(R.id.people);
        mGrades = (CardView) findViewById(R.id.grades);
        mFontysNews = (CardView) findViewById(R.id.news);
        mAboutme = (CardView) findViewById(R.id.aboutme);
        // Adding click listener
        mSchedule.setOnClickListener(this);
        mPeople.setOnClickListener(this);
        mGrades.setOnClickListener(this);
        mFontysNews.setOnClickListener(this);
        mAboutme.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.schedule:
                i = new Intent(this, ScheduleActivity.class);
                startActivity(i);
                break;
            case R.id.people:
                i = new Intent(this,GradesActivity.class);
                startActivity(i);
                break;
            case R.id.grades:
                i = new Intent(this,GradesActivity.class);
                startActivity(i);
                break;
            case R.id.news:
                i = new Intent(this,NewsActivity.class);
                startActivity(i);
                break;
            case R.id.aboutme:
                i = new Intent(this,AboutMeActivity.class);
                startActivity(i);
                break;
            default:
                Log.d("error", "onClick: Nothing was CLICKKKEDDDD");
                break;
        }
    }
}
