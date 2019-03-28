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
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.haroon.routines.R;
import com.haroon.routines.dal.RoutineDB;
import com.haroon.routines.models.Routine;

import java.util.ArrayList;
import java.util.Date;

public class RoutineService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        createNotificationChannel();

        Routine currentRoutine = RoutineDB.getCurrentRoutine();

        Notification.Builder builder = new Notification.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_timelapse_black_24dp)
                .setContentTitle(currentRoutine.name)
                .setWhen(currentRoutine.endDate.getTime() + 10000)
                .setUsesChronometer(true)
                .setChronometerCountDown(true);


        setAlarm(currentRoutine.endDate.getTime());

        startForeground(NOTIFICATION_ID, builder.build());

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Routines", NotificationManager.IMPORTANCE_NONE);
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(notificationChannel);
    }

    private void setAlarm(long alarmTime) {
        Intent broadcastIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, 0);
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent);

    }

    private static String CHANNEL_ID = "RoutineChannel";
    private static int NOTIFICATION_ID = 1002;

    private class AlarmReceiver extends BroadcastReceiver {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onReceive(Context context, Intent intent) {
            String text = "Some text that will update the notification";

            Notification.Builder builder = new Notification.Builder(RoutineService.this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_timelapse_black_24dp)
                    .setContentTitle("TEST")
                    .setWhen(new Date().getTime() + 10000)
                    .setUsesChronometer(true)
                    .setChronometerCountDown(true);

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }

}