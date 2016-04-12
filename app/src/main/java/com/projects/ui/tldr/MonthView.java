package com.projects.ui.tldr;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

public class MonthView extends AppCompatActivity {
    CalendarView cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_view);
        cal = (CalendarView) findViewById(R.id.calendarView1);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getBaseContext(),"Selected Date is\n\n" + dayOfMonth + " / " + month + " / " + year, Toast.LENGTH_LONG).show();
            }
        });

    }

}
