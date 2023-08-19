package com.example.myapplication;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.RequiresPermission;

public class NotificationReceiver extends BroadcastReceiver {
    private final String CHANNEL_ID = "channelId";
    private final LockscreenNotificationManager mLockscreenNotificationManager;

    public NotificationReceiver(LockscreenNotificationManager lockscreenNotificationManager) {
        mLockscreenNotificationManager = lockscreenNotificationManager;
    }

    @Override
    @RequiresPermission(value = Manifest.permission.POST_NOTIFICATIONS)
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_SCREEN_ON.equals(action))
            GlobalThreadPool.submit(() -> mLockscreenNotificationManager.sendNotification(context));
    }

}
