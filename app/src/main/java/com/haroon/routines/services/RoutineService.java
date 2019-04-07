package com.haroon.routines.services;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.os.IBinder;
import android.provider.MediaStore;
import android.provider.Settings;

import com.haroon.routines.MainActivity;
import com.haroon.routines.R;
import com.haroon.routines.receivers.AlarmReceiver;

public class RoutineService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (ACTION_STOP_SERVICE.equals(intent.getAction())) {
            stopSelf();
        }

        createNotificationChannel();
        sendAlarmBroadcast();

        startForeground(NOTIFICATION_ID, buildNotification());
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Routines", NotificationManager.IMPORTANCE_NONE);
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        notificationChannel.enableVibration(true);
        notificationChannel.setSound(Settings.System.DEFAULT_ALARM_ALERT_URI, null);

        manager.createNotificationChannel(notificationChannel);
    }

    private Notification buildNotification() {
        Notification.Builder builder = new Notification.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_timelapse_black_24dp)
                .setContentTitle("Routine")
                .setContentText("Service is starting...");

        return builder.build();
    }

    private void sendAlarmBroadcast() {
        Intent brIntent = new Intent(this, AlarmReceiver.class);
        sendBroadcast(brIntent);
    }

    public static final String CHANNEL_ID = "RoutineChannel";
    public static final int NOTIFICATION_ID = 1002;
    public static final String ACTION_STOP_SERVICE = "RoutineStopService";
}