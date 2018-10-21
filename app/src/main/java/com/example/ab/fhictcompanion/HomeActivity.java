package com.example.ab.fhictcompanion;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView mSchedule, mPeople, mLocation, mAboutme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Linking
        mSchedule = (CardView) findViewById(R.id.schedule);
        mPeople = (CardView) findViewById(R.id.people);
        mLocation = (CardView) findViewById(R.id.location);
        mAboutme = (CardView) findViewById(R.id.aboutme);
        // Adding click listener
        mSchedule.setOnClickListener(this);
        mPeople.setOnClickListener(this);
        mLocation.setOnClickListener(this);
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
                i = new Intent(this,PeopleActivity.class);
                startActivity(i);
                break;
            case R.id.aboutme:
                i = new Intent(this,AboutMeActivity.class);
                startActivity(i);
                break;
            case R.id.location:
                Uri geoIntengUri = Uri.parse("geo:51.452095,5.481963?q=" + Uri.encode("Fontys - Campus Rachelsmolen"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoIntengUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;
            default:
                Log.d("error", "onClick: Nothing was CLICKKKEDDDD");
                break;
        }
    }
}
