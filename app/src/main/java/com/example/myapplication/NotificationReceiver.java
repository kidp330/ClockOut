package com.example.myapplication;

import static android.os.SystemClock.sleep;
import static androidx.media3.common.util.NotificationUtil.createNotificationChannel;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Arrays;
import java.util.Objects;

public class NotificationReceiver extends BroadcastReceiver {
    private final String CHANNEL_ID = "channelId";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON) == false)
            return;
        Log.e("dupa", "Inside receiver");
        PendingIntent fsi = PendingIntent.getActivity(context, 0, new Intent(context, LockscreenActivity.class), PendingIntent.FLAG_MUTABLE);
        NotificationCompat.Builder b = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("dupa title")
                .setContentText("dupa text")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setFullScreenIntent(fsi, true);

        NotificationChannel nc = new NotificationChannel(CHANNEL_ID, "dupaChannelName", NotificationManager.IMPORTANCE_HIGH);

        NotificationManagerCompat nm = (NotificationManagerCompat) NotificationManagerCompat.from(context);
        nm.createNotificationChannel(nc);
        Notification n = b.build();
        if (ActivityCompat.checkSelfPermission(context.getApplicationContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.e("dupa :o", "HERE");
            return;
        }
        nm.notify(0, n);
        Log.e("dupa", String.format("nm: %s", n.getChannelId()));
        Log.e("dupa", String.format("nm: %s", n.describeContents()));
    }
}
