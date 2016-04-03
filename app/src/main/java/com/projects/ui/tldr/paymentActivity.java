package com.projects.ui.tldr;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        setContentView(R.layout.activity_payment);

        mContext = this;

        mSpassFingerprint = new SpassFingerprint(paymentActivity.this);
        spass = new Spass();
        try{
            spass.initialize(mContext);
        } catch (Exception e){
            Toast.makeText(paymentActivity.this, "Exception thrown while initializing Fingerprint manager", Toast.LENGTH_SHORT).show();
            //Handle exception
        }
        mSpassFingerprint.startIdentifyWithDialog(mContext, listener, successAuth );

        isFeatureEnabled = spass.isFeatureEnabled(Spass.DEVICE_FINGERPRINT);




    }
}
