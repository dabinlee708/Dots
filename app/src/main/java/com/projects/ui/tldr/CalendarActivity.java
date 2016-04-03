package com.projects.ui.tldr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    }

    public void onClick(View v) {
        Log.d("mytag", "onClick:UI" + v.getId());
        TextView text = (TextView) findViewById(v.getId());

        LinearLayout event_details = new LinearLayout(this);
        TextView event_name = new TextView(this);
        event_name.setText(text.getText());

        Button button = new Button(this);
        button.setText("More Details");
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CalendarActivity.this, EventActivity.class));
            }

        });
        button.setId(View.generateViewId());

        event_details.addView(event_name);
        event_details.addView(button);

        LinearLayout layout = (LinearLayout) findViewById(R.id.linlayout);
        layout.addView(event_details);

    }
}
