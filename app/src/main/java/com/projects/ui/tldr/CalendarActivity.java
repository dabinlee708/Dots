package com.projects.ui.tldr;

import android.app.Dialog;
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
        ImageView image = (ImageView) dialog.findViewById(R.id.logo);
        image.setImageResource(R.drawable.noon);
        ImageView image2 = (ImageView) dialog.findViewById(R.id.logo2);
        image2.setImageResource(R.drawable.money);
        ImageView image3 = (ImageView) dialog.findViewById(R.id.logo3);
        image3.setImageResource(R.drawable.night);

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

//        TextView selection = (TextView) findViewById(R.id.selected);
//        selection.setText(text.getText());
//        Button btnDetails = (Button) findViewById(R.id.button);
//        btnDetails.setVisibility(v.VISIBLE);
//        btnDetails.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent myIntent = new Intent(CalendarActivity.this, EventActivity.class);
//                myIntent.putExtra("T", selection.getText());
//                startActivity(myIntent);
//            }
//
//        });
        ScrollView scr = (ScrollView) findViewById(R.id.scrlayout);
        scr.fullScroll(ScrollView.FOCUS_DOWN);


    }
}
