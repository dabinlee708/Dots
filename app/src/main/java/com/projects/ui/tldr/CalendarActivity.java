package com.projects.ui.tldr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        final ImageView caret = (ImageView) findViewById(R.id.caret);
        caret.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LinearLayout toolbar = (LinearLayout) findViewById(R.id.toolbar);
                if (toolbar.getVisibility() == View.GONE) {
                    toolbar.setVisibility(View.VISIBLE);
                    caret.setImageResource(R.drawable.caretup);
                } else{
                    toolbar.setVisibility(View.GONE);
                    caret.setImageResource(R.drawable.caretdown);
                }
            }
        });
    }

    public void onClick(View v) {
        Log.d("mytag", "onClick:UI" + v.getId());
        TextView text = (TextView) findViewById(v.getId());

//        LinearLayout event_details = new LinearLayout(this);
//        TextView event_name = new TextView(this);
//        event_name.setText(text.getText());
//
//        Button button = new Button(this);
//        button.setText("More Details");
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(CalendarActivity.this, EventActivity.class));
//            }
//
//        });
//        button.setId(View.generateViewId());
//
//        event_details.addView(event_name);
//        event_details.addView(button);
//
//        LinearLayout layout = (LinearLayout) findViewById(R.id.linlayout);
//        layout.addView(event_details);
        final TextView selection = (TextView) findViewById(R.id.selected);
        selection.setText(text.getText());
        Button btnDetails = (Button) findViewById(R.id.button);
        btnDetails.setVisibility(v.VISIBLE);
        btnDetails.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                startActivity(new Intent(CalendarActivity.this, EventActivity.class));
                Intent myIntent = new Intent(CalendarActivity.this, EventActivity.class);
                myIntent.putExtra("T", selection.getText());
                startActivity(myIntent);
            }
        });
        ScrollView scr = (ScrollView) findViewById(R.id.scrlayout);
        scr.fullScroll(ScrollView.FOCUS_DOWN);


    }
}
