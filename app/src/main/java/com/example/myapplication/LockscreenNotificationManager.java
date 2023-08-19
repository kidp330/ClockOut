package com.example.myapplication;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.RequiresPermission;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class LockscreenNotificationManager {

    private static final String LOCKSCREEN_CHANNEL_ID = "LOCKSCREEN_CHANNEL_ID";
    private static final CharSequence LOCKSCREEN_CHANNEL_NAME = "Lockscreen channel";
    private static final int GET_LOCKSCREEN_ACTIVITY_RC = 0;
    private static final int LOCKSCREEN_NOTIFICATION_ICON = android.R.drawable.arrow_up_float;
    private static final CharSequence LOCKSCREEN_NOTIFICATION_TITLE = "You shouldn't be seeing this";
    private static final String LOCKSCREEN_NOTIFICATION_TEXT = "Sunrise, Parabellum";
    private final NotificationChannelCompat mNotificationChannel;
    private static final int NOTIFICATION_ID = 0;

    private final NotificationManagerCompat mNotificationManager;

    public LockscreenNotificationManager(Context context) {
        mNotificationManager = NotificationManagerCompat.from(context);
        mNotificationChannel = createChannel();
        registerChannel();
    }

    @RequiresPermission(value = Manifest.permission.POST_NOTIFICATIONS)
    public void sendNotification(Context context) {
        Notification notification = createNotification(context);
        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }

    public void cancelNotification() {
        mNotificationManager.cancel(NOTIFICATION_ID);
    }

    private NotificationChannelCompat createChannel() {
        NotificationChannelCompat.Builder builder = new NotificationChannelCompat.Builder(
            LOCKSCREEN_CHANNEL_ID,
            NotificationManagerCompat.IMPORTANCE_HIGH
        );
        builder.setName(LOCKSCREEN_CHANNEL_NAME);
        return builder.build();
    }

    private void registerChannel() {
        mNotificationManager.createNotificationChannel(mNotificationChannel);
    }

    private Notification createNotification(Context context) {
        Intent startLockscreenActivity = new Intent(context, LockscreenActivity.class);
        PendingIntent pStartLockscreenActivity = PendingIntent.getActivity(
            context,
            GET_LOCKSCREEN_ACTIVITY_RC,
            startLockscreenActivity,
            PendingIntent.FLAG_IMMUTABLE
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
            context, mNotificationChannel.getId())
            .setSmallIcon(LOCKSCREEN_NOTIFICATION_ICON)
            .setContentTitle(LOCKSCREEN_NOTIFICATION_TITLE)
            .setContentText(LOCKSCREEN_NOTIFICATION_TEXT)
            .setFullScreenIntent(pStartLockscreenActivity, true);

        return builder.build();
    }
}
