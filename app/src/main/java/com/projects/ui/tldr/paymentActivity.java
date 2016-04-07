package com.projects.ui.tldr;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.samsung.android.sdk.pass.Spass;
import com.samsung.android.sdk.pass.SpassFingerprint;

public class paymentActivity extends AppCompatActivity {
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
                Toast.makeText(paymentActivity.this, "Authentication success", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(paymentActivity.this, "Authentication fail", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onReady() {
            Toast.makeText(paymentActivity.this, "Identify state is ready", Toast.LENGTH_SHORT);

        }

        @Override
        public void onStarted() {
            Toast.makeText(paymentActivity.this, "Fingerprint sensor touched", Toast.LENGTH_SHORT);
        }

        @Override
        public void onCompleted() {

        }
    };

    private SpassFingerprint.RegisterListener mRegisterListener = new SpassFingerprint.RegisterListener(){
        @Override
        public void onFinished(){
            Toast.makeText(paymentActivity.this, "Register listener finished", Toast.LENGTH_SHORT).show();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_payment);

        mContext = this;

        mSpassFingerprint = new SpassFingerprint(paymentActivity.this);
        mSpassFingerprint.setDialogBgTransparency(0);
        spass = new Spass();
        try{
            spass.initialize(mContext);
            mSpassFingerprint.startIdentifyWithDialog(mContext, listener, successAuth );
        } catch (Exception e){
            Toast.makeText(paymentActivity.this, "Automatic override for authetntication", Toast.LENGTH_LONG).show();
            successAuth=true;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(paymentActivity.this,CalendarActivity.class);
                startActivity(i);
            }
        }, 2000);




//        isFeatureEnabled = spass.isFeatureEnabled(Spass.DEVICE_FINGERPRINT);




    }
}
