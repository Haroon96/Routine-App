package com.haroon.routines.receivers;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.support.v4.graphics.drawable.IconCompat;

import com.haroon.routines.MainActivity;
import com.haroon.routines.R;
import com.haroon.routines.dal.RoutineDB;
import com.haroon.routines.models.Task;
import com.haroon.routines.services.RoutineService;

import java.util.Date;

import static com.haroon.routines.services.RoutineService.ACTION_STOP_SERVICE;
import static com.haroon.routines.services.RoutineService.CHANNEL_ID;
import static com.haroon.routines.services.RoutineService.NOTIFICATION_ID;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Task task = RoutineDB.getNextTask();

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, buildNotification(context, task));

        setAlarm(context, task.endTime.getTime() + 10000);
    }

    public Notification buildNotification(Context context, Task task) {

        Intent stopIntent = new Intent(context, RoutineService.class);
        stopIntent.setAction(ACTION_STOP_SERVICE);
        PendingIntent stopSelf = PendingIntent.getService(context, 0, stopIntent,PendingIntent.FLAG_CANCEL_CURRENT);

        Intent openIntent = new Intent(context, MainActivity.class);
        PendingIntent openSelf = PendingIntent.getActivity(context, 0, openIntent, 0);

        Notification.Action action = new Notification.Action
                .Builder(Icon.createWithResource(context, R.drawable.ic_delete_black_24dp), "Stop", stopSelf).build();

        Notification.Builder builder = new Notification.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_timelapse_black_24dp)
                .setContentTitle(task.name)
                .setBadgeIconType(Notification.BADGE_ICON_NONE)
                .setWhen(task.endTime.getTime())
                .setUsesChronometer(true)
                .setChronometerCountDown(true)
                .setContentIntent(openSelf)
                .addAction(action);

        return builder.build();
    }

    public void setAlarm(Context context, long alarmTime) {
        Intent broadcastIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, broadcastIntent, 0);
        AlarmManager manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent);
    }

}