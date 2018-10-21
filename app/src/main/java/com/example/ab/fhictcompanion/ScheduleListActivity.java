package com.example.ab.fhictcompanion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import com.example.ab.fhictcompanion.Adapters.ScheduleAdapter;
import com.example.ab.fhictcompanion.Model.Schedule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ScheduleListActivity extends AppCompatActivity {
    private TextView mDateText;
    private String mDate;
    private ArrayList<Schedule> scheduleList, todaySchedule;
    private RecyclerView mRecyclerView;
    private ScheduleAdapter mAdapter;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);

        mDialog = new ProgressDialog(ScheduleListActivity.this);

        mDateText = (TextView) findViewById(R.id.dateText);
        scheduleList = new ArrayList<>();
        todaySchedule = new ArrayList<>();

        Intent intent = getIntent();
        mDate = intent.getStringExtra("date");

        mDateText.setText(mDate);

        new JSONTask().execute();

    }

    public class JSONTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {

            try {
                URL url = new URL("https://api.fhict.nl/schedule/me");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("Authorization", "Bearer " + StartActivity.token);
                connection.connect();

                InputStream is = new BufferedInputStream(connection.getInputStream());
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                String response = sb.toString();

                JSONObject json = new JSONObject(response);
                JSONArray jsonArray = json.getJSONArray("data");
                for(int i=0; i<jsonArray.length(); i++){
                    JSONObject data = jsonArray.getJSONObject(i);
                    String subject = data.getString("subject");
                    String subjectName = data.optString("subjectName");
                    String teacher = data.getString("teacherAbbreviation");
                    String room = data.getString("room");
                    String startData = data.getString("start").substring(0,10);
                    String startTime = data.getString("start").substring(11);
                    String endTime = data.getString("end").substring(11);
                    int colorLine = Color.WHITE;

                    scheduleList.add(new Schedule(subject, subjectName, teacher, room, startData, startTime, endTime, colorLine));

                    if(scheduleList.get(i).getStartDate().equals(mDate)){
                        switch (scheduleList.get(i).getSubject()){
                            case "popd3":
                                colorLine = Color.CYAN;
                                break;
                            case "os1":
                                colorLine = Color.GRAY;
                                break;
                            case "andr1":
                                colorLine = Color.GREEN;
                                break;
                            case "sd3":
                                colorLine = Color.YELLOW;
                                break;
                            case "procp":
                                colorLine = Color.rgb(0, 255, 169);
                                break;
                            default:
                                colorLine = Color.WHITE;
                                break;
                        }
                        todaySchedule.add(new Schedule(scheduleList.get(i).getSubject(), scheduleList.get(i).getSubjectName(),
                                scheduleList.get(i).getTeacher(), scheduleList.get(i).getRoom(),
                                scheduleList.get(i).getStartDate(), scheduleList.get(i).getStartTime(),
                                scheduleList.get(i).getEndTime(), colorLine));
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialog.setTitle("Loading");
            mDialog.setMessage("Please wait...");
            mDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mDialog.dismiss();

            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewSchedule);
            mAdapter = new ScheduleAdapter(todaySchedule);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(ScheduleListActivity.this));
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
