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
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context,title,duration);
        toast.show();
        TextView titleEvent = (TextView) findViewById(R.id.title_of_event);
        titleEvent.setText(title);
    }

    public void onClick(View v) {
        Log.d("mytag", "onClick:UI" + v.getId());
        TextView text = (TextView) findViewById(v.getId());
        Button btnConfirmation = (Button) findViewById(R.id.confirmation);
        btnConfirmation.setVisibility(v.VISIBLE);
        btnConfirmation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("mytag","onclickclickclick"+ v.getId());
                startActivity(new Intent(EventActivity.this, paymentActivity.class));
            }
        });

    }
}
