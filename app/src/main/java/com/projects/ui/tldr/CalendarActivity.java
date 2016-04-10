package com.projects.ui.tldr;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity{
    private float x1,x2;
    static final int MIN_DISTANCE = 150;
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
                } else {
                    toolbar.setVisibility(View.GONE);
                    caret.setImageResource(R.drawable.caretdown);
                }
            }
        });


        final Button[] blist = {(Button)findViewById(R.id.ISTD),
                (Button)findViewById(R.id.EPD),
                (Button)findViewById(R.id.ASD),
                (Button)findViewById(R.id.ESD),
                (Button)findViewById(R.id.fifth)};
        final TextView[] tlist = {(TextView) findViewById(R.id.musicbox),
                            (TextView) findViewById(R.id.study),
                            (TextView) findViewById(R.id.chill)};

        //SET ONCLICK FUNCTION FOR ALL
        Button b = (Button) findViewById(R.id.ALL);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v.setBackgroundResource(R.drawable.circlebuttonselected);
                for (Button i: blist){
                    i.setBackgroundResource(R.drawable.circlebuttondeselected);
                }
                for (TextView j: tlist){
                    j.setVisibility(View.VISIBLE);
                }
            }
        });
        //SET ONCLICK FUNCTION FOR ALL OTHER BUTTONS
        for (Button i:blist){
            i.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Button b = (Button)v;
                    Log.d("mytag", "onClick:button" + b.getText());

                    findViewById(R.id.ALL).setBackgroundResource(R.drawable.circlebuttondeselected);
                    v.setBackgroundResource(R.drawable.circlebuttonselected);
                    for (TextView j: tlist) {
                        j.setVisibility(View.GONE);
                    }

                    //THIS LINE OF CODE DOESN'T WORK FOR SOME REASON
                    if(b.getText()=="ISTD"){
                        findViewById(R.id.study).setVisibility(View.VISIBLE);
                    }
                    //THIS LINE OF CODE DOESN'T WROK
                    else if(b.getText()=="5th"){
                        findViewById(R.id.chill).setVisibility(View.VISIBLE);
                        findViewById(R.id.musicbox).setVisibility(View.VISIBLE);
                    }
                    else{

                    }
                }
            });
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                if (Math.abs(deltaX) > MIN_DISTANCE)
                {

                    Intent myIntent = new Intent(CalendarActivity.this, EventActivity.class);
                    myIntent.putExtra("T", "test");
                    startActivity(myIntent);
                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void onClick(Button b){
        Log.d("mytag", "onClick:button" + b.getText());
        Button[] blist = {(Button)findViewById(R.id.ISTD),
                (Button)findViewById(R.id.EPD),
                (Button)findViewById(R.id.ASD),
                (Button)findViewById(R.id.ESD),
                (Button)findViewById(R.id.fifth)};

        if (b.getText()=="ALL"){
            for (Button i : blist){
                i.setBackgroundResource(R.drawable.circlebuttondeselected);
            }
        }
        else{
            Button button = (Button)findViewById(R.id.ALL);
            button.setBackgroundResource(R.drawable.circlebuttondeselected);
            b.setBackgroundResource(R.drawable.circlebuttonselected);
        }
    }

    public void onClick(View v) {
        Log.d("mytag", "onClick:text" + v.getId());
        TextView text = (TextView) findViewById(v.getId());

        final TextView selection = (TextView) findViewById(R.id.selected);
        selection.setText(text.getText());

        final Dialog dialog = new Dialog(CalendarActivity.this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.dialog);
        // Set dialog title
        dialog.setTitle("Custom Dialog");

        // set values for custom dialog components - text, image and button
        TextView title = (TextView) dialog.findViewById(R.id.title);
        title.setText(selection.getText());
        TextView eventDetails = (TextView) dialog.findViewById(R.id.details);
        eventDetails.setText("Need to dynamically add hardcoded details for the events based on the available choices.\nPerhaps choose a few to demo.");
        ImageView image = (ImageView) dialog.findViewById(R.id.logo);
        image.setImageResource(R.drawable.noon);
        ImageView image2 = (ImageView) dialog.findViewById(R.id.logo2);
        image2.setImageResource(R.drawable.money);
        ImageView image3 = (ImageView) dialog.findViewById(R.id.logo3);
        image3.setImageResource(R.drawable.nomoney);
        ImageView image4 = (ImageView) dialog.findViewById(R.id.logo4);
        image4.setImageResource(R.drawable.nomoney);

        dialog.show();

        Button accept = (Button) dialog.findViewById(R.id.acceptButton);
        // if decline button is clicked, close the custom dialog
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
            }
        });


        Button details = (Button) dialog.findViewById(R.id.detailsButton);
        // if decline button is clicked, close the custom dialog
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                Intent myIntent = new Intent(CalendarActivity.this, EventActivity.class);
                myIntent.putExtra("T", selection.getText());
                startActivity(myIntent);

            }
        });

//        ScrollView scr = (ScrollView) findViewById(R.id.scrlayout);
//        scr.fullScroll(ScrollView.FOCUS_DOWN);


    }



}
