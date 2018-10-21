package com.example.ab.fhictcompanion;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class ScheduleActivity extends AppCompatActivity {

    private MaterialCalendarView mCalendarView;
    private String mDate, mDay, mMonth, mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        mCalendarView = (MaterialCalendarView) findViewById(R.id.datePicker);
        mCalendarView.setSelectedDate(CalendarDay.today());

        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mDay = String.valueOf(date.getDay());
                mMonth = String.valueOf(date.getMonth()+1);
                mYear = String.valueOf(date.getYear());

                validateDate(mDay, mMonth);
                mDate = mYear+ "-" +mMonth+ "-" +mDay;

                Intent intent = new Intent();
                intent.putExtra("date", mDate);
                intent.setClass(ScheduleActivity.this,ScheduleListActivity.class);
                startActivity(intent);
            }
        });

    }

    public void validateDate(String date, String month){
        if(date.length() == 1){
            mDay = "0" +mDay;
        }

        if(month.length() == 1){
            mMonth = "0" +mMonth;
        }
    }
}
