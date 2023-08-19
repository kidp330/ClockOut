package com.example.myapplication;

import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LockscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setShowWhenLocked(true);
        setTurnScreenOn(true);
        KeyguardManager km = (KeyguardManager) this.getSystemService(KEYGUARD_SERVICE);
        km.requestDismissKeyguard(this, new KeyguardManager.KeyguardDismissCallback() {
            @Override
            public void onDismissError() { Log.e("dupa", "errored for some reason"); }
            @Override
            public void onDismissCancelled() { Log.e("dupa", "cancelled for some reason"); }
        });

        Button btn = (Button) findViewById(R.id.rebindButton);
        btn.setText("dupka");
    }
}
