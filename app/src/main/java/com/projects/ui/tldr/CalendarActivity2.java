package com.projects.ui.tldr;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarActivity2 extends AppCompatActivity{
    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);

        final Button[] blist = {(Button)findViewById(R.id.ISTD),
                (Button)findViewById(R.id.EPD),
                (Button)findViewById(R.id.ASD),
                (Button)findViewById(R.id.ESD),
                (Button)findViewById(R.id.fifth)};
        final TextView[] tlist = {(TextView) findViewById(R.id.musicbox),
//                            (TextView) findViewById(R.id.study),
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

        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(CalendarActivity2.this, MonthView.class);
                startActivity(myIntent);
            }
        });

        //SET ONCLICK FUNCTION FOR ALL OTHER BUTTONS
        for (Button i:blist){
            i.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Button b = (Button)v;
                    Log.d("mytag", "onClick:button" +b.getText());

                    findViewById(R.id.ALL).setBackgroundResource(R.drawable.circlebuttondeselected);
                    v.setBackgroundResource(R.drawable.circlebuttonselected);
                    for (TextView j: tlist) {
                        j.setVisibility(View.GONE);
                    }

                    //THIS LINE OF CODE DOESN'T WORK FOR SOME REASON
                    if(b.getText().equals("ISTD")){
//                        findViewById(R.id.study).setVisibility(View.VISIBLE);
                    }
                    //THIS LINE OF CODE DOESN'T WROK
                    else if(b.getText().equals("5th")){
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
                if (Math.abs(deltaX) > MIN_DISTANCE && deltaX < 0)
                {

                }
                else if (Math.abs(deltaX) > MIN_DISTANCE && deltaX > 0)
                {
                    Intent myIntent = new Intent(CalendarActivity2.this, CalendarActivity.class);
                    startActivity(myIntent);

                }
                break;

        }
        return super.onTouchEvent(event);
    }

    public void onClick(View v) {
        Log.d("mytag", "onClick:text" + v.getId());
        final TextView text = (TextView) findViewById(v.getId());

        final Dialog dialog = new Dialog(CalendarActivity2.this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.dialog);
        // Set dialog title


        // set values for custom dialog components - text, image and button
        TextView title = (TextView) dialog.findViewById(R.id.title);
        dialog.setTitle(text.getText());
        TextView eventDetails = (TextView) dialog.findViewById(R.id.details);
        TextView text2 = (TextView) findViewById(v.getId());
        String a = ""+text2.getText();
        if (a.equals("CT")){
            title.setText("OMS X - The Finale");
            eventDetails.setText("TEST OMS TEXT TEST OMS TEXT\nTEST OMS TEXTTEST OMS TEXT");
        }
        else if (a.equals("MB")){
            title.setText("The Music Box");
            eventDetails.setText("TEXT MUSIC BOX");
        }
        else if (a.equals("CAP")){
            title.setText("Chill Time");
            eventDetails.setText("TEST CTFO TEXT TEST CTFO TEXT\n" +
                    "TEST CTFO TEXT TEST CTFO TEXT");
        }

        title.setGravity(View.TEXT_ALIGNMENT_CENTER);
        eventDetails.setGravity(View.TEXT_ALIGNMENT_CENTER);
        ImageView image = (ImageView) dialog.findViewById(R.id.logo2);
        image.setImageResource(R.drawable.money);
        ImageView image2 = (ImageView) dialog.findViewById(R.id.logo3);
        image2.setImageResource(R.drawable.people9);
//        ImageView image3 = (ImageView) dialog.findViewById(R.id.logo3);
//        image3.setImageResource(R.drawable.nomoney);
//        ImageView image4 = (ImageView) dialog.findViewById(R.id.logo4);
//        image4.setImageResource(R.drawable.night);


        dialog.show();
        mContext = this;
        Button accept = (Button) dialog.findViewById(R.id.acceptButton);
        accept.setBackgroundResource(R.drawable.tick);
        // if decline button is clicked, close the custom dialog
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                String a = ""+text.getText();
                switch(a) {
                    case ("MB"):
                        TextView oms = (TextView) findViewById(R.id.musicbox);
                        oms.setVisibility(View.INVISIBLE);
                        TextView oms2 = (TextView) findViewById(R.id.musicbox2);
                        oms2.setVisibility(View.VISIBLE);
                        TextView back = (TextView) findViewById(R.id.backgrey);
                        back.setVisibility(View.INVISIBLE);
                }
                dialog.dismiss();
            }
        });

        Button details = (Button) dialog.findViewById(R.id.detailsButton);
        // if decline button is clicked, close the custom dialog
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                Intent myIntent = new Intent(CalendarActivity2.this, EventActivity.class);
                myIntent.putExtra("T", text.getText());
                startActivity(myIntent);

            }
        });
    }
}
