package com.projects.ui.tldr;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.provider.CalendarContract;
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
import android.widget.Toast;

import com.samsung.android.sdk.pass.Spass;
import com.samsung.android.sdk.pass.SpassFingerprint;

import java.sql.Array;
import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity{
    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    private boolean onReadyIdentify = false;
    private SpassFingerprint mSpassFingerprint;
    private Spass spass;
    private Context mContext;
    boolean isFeatureEnabled = false;
    boolean successAuth;

    private SpassFingerprint.IdentifyListener listener = new SpassFingerprint.IdentifyListener() {
        @Override
        public void onFinished(int eventStatus) {
            onReadyIdentify = false;
            if(eventStatus==SpassFingerprint.STATUS_AUTHENTIFICATION_SUCCESS||eventStatus==SpassFingerprint.STATUS_AUTHENTIFICATION_PASSWORD_SUCCESS){
                Toast.makeText(CalendarActivity.this, "Authentication success", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(CalendarActivity.this, "Authentication fail", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onReady() {
            Toast.makeText(CalendarActivity.this, "Identify state is ready", Toast.LENGTH_SHORT);

        }

        @Override
        public void onStarted() {
            Toast.makeText(CalendarActivity.this, "Fingerprint sensor touched", Toast.LENGTH_SHORT);
        }

        @Override
        public void onCompleted() {

        }
    };

    private SpassFingerprint.RegisterListener mRegisterListener = new SpassFingerprint.RegisterListener(){
        @Override
        public void onFinished(){
            Toast.makeText(CalendarActivity.this, "Register listener finished", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

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

//        Button tmr = (Button) findViewById(R.id.nextbtn);
//        // if decline button is clicked, close the custom dialog
//        tmr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Close dialog
//                setContentView(R.layout.activity_calendar2);
//
//            }
//        });
//
        Button nth = (Button) findViewById(R.id.mthbtn);
        // if decline button is clicked, close the custom dialog
        nth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(CalendarActivity.this, MonthView.class);
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

                    if(b.getText().equals("ISTD")){
                        findViewById(R.id.study).setVisibility(View.VISIBLE);
                    }

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
                    Intent myIntent = new Intent(CalendarActivity.this, CalendarActivity2.class);
                    startActivity(myIntent);
                }
                else if (Math.abs(deltaX) > MIN_DISTANCE && deltaX > 0)
                {


                }
                break;

        }
        return super.onTouchEvent(event);
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


        // set values for custom dialog components - text, image and button
        //TextView title = (TextView) dialog.findViewById(R.id.title);
        dialog.setTitle(selection.getText());
//        title.setText(selection.getText());
        //title.setGravity(View.TEXT_ALIGNMENT_CENTER);
        TextView eventDetails = (TextView) dialog.findViewById(R.id.details);
        eventDetails.setText("Need to dynamically add hardcoded details for the events based on the available choices.\nPerhaps choose a few to demo.");
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



                try {
                    mSpassFingerprint = new SpassFingerprint(CalendarActivity.this);
                    mSpassFingerprint.setDialogBgTransparency(0);
                    spass = new Spass();
                    spass.initialize(mContext);
                    mSpassFingerprint.startIdentifyWithDialog(mContext, listener, successAuth);
                } catch (Exception e) {
                    Toast.makeText(CalendarActivity.this, "Automatic override for authetntication", Toast.LENGTH_LONG).show();
                    successAuth = true;
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(CalendarActivity.this, CalendarActivity.class);
                        startActivity(i);
                    }
                }, 2000);
            }
        });




        Button details = (Button) dialog.findViewById(R.id.detailsButton);
        details.setBackgroundResource(R.drawable.redirect);
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
