package com.example.myapplication;

import static android.os.SystemClock.sleep;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private final int POST_NOTIFICATIONS_REQUEST_CODE = 100;
    private final int TRIGGER_RECEIVER_MILLIS = 7_000;

    private NotificationReceiver mNotificationReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.POST_NOTIFICATIONS }, POST_NOTIFICATIONS_REQUEST_CODE );

        setupScheduleButton();
        // TODO: skip this on permission denied and show user info that LS will not work properly
        registerNotificationReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNotificationReceiver);
        GlobalThreadPool.getInstance().shutdownNow(); // __jm__ this might force quit working threads
    }

    private void setupScheduleButton() {
        Button btn = (Button) findViewById(R.id.rebindButton);
        btn.setOnClickListener(view -> {
            Log.e("dupa", "scheduling a full screen intent");
            Context context = getApplicationContext();
            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, new Intent(context, NotificationReceiver.class), PendingIntent.FLAG_IMMUTABLE);
            am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + TRIGGER_RECEIVER_MILLIS, pi);
        });
    }

    private void registerNotificationReceiver() {
        LockscreenNotificationManager manager = new LockscreenNotificationManager(this);
        NotificationReceiver mNotificationReceiver = new NotificationReceiver(manager);

        IntentFilter screenActionsFilter = new IntentFilter();
        screenActionsFilter.addAction(Intent.ACTION_SCREEN_ON);
        screenActionsFilter.addAction(Intent.ACTION_SCREEN_OFF);

        registerReceiver(mNotificationReceiver, screenActionsFilter);
    }
}