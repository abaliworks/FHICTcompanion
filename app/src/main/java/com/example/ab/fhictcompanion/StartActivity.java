package com.example.ab.fhictcompanion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class StartActivity extends AppCompatActivity implements TokenFragment.OnFragmentInteractionListener {
    public static String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    @Override
    public void onFragmentInteraction(String token) {
        this.token =token;
        Log.d("YOOOOOOO", token);
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        intent.putExtra("token", token);
        startActivity(intent);
        finish();
    }
}
