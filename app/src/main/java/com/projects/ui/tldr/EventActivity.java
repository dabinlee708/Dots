package com.projects.ui.tldr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by User on 4/4/2016.
 */
public class EventActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event);
        Intent newIntent = getIntent();
        String title = newIntent.getExtras().getString("T");
        TextView titleEvent = (TextView) findViewById(R.id.title_of_event);
        titleEvent.setText(title);

        Button btnConfirmation = (Button) findViewById(R.id.confirmation);
        btnConfirmation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(EventActivity.this, paymentActivity.class));
            }
        });
    }

}
