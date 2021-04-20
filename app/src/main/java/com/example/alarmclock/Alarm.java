package com.example.alarmclock;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

public class Alarm extends BroadcastReceiver {
    private final int notification_id = new Random().nextInt();
    private NotificationManagerCompat notificationManager;
    private PendingIntent pi;
    private MediaPlayer mp;


    public Alarm(){}//Need to find a way to get uriSound in timepicker fragment



    @Override
    public void onReceive(Context context, Intent intent) {
        //MainActivity.openSnoozeDismissPage();
        Intent notificationIntent = new Intent(context,dismiss_snooze.class);
        pi = PendingIntent.getActivity(context, 0, notificationIntent, 0);


        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "");
        wl.acquire();

        alarmNotification(context);
        makeNoise(context);

        wl.release();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setAlarm(Context context, Long time) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.set(AlarmManager.RTC_WAKEUP, time, pi);
    }

    public void cancelAlarm(Context context) {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

    public void makeNoise(Context context) {
        Uri uriSound = ((application)context).urisound;
        try {
            System.out.println(uriSound);
            mp = new MediaPlayer();
            if(uriSound!=null) {
                mp.setDataSource(context, uriSound);
                mp.setVolume(1,1);
                mp.prepare();
            } else {
                mp = MediaPlayer.create(context, R.raw.loud_alarm_clock);
            }
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopNoise(View view) {
        mp.release();
    }

    private void alarmNotification(Context context)
    {
        int NOTIFICATION_ID = 234;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        String CHANNEL_ID = "my_channel_01";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "my_channel";
            String Description = "Notification channel for alarm app.";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        Uri soundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ context.getPackageName() + "/" + R.raw.loud_alarm_clock);

        Intent resultIntent = new Intent(context, dismiss_snooze.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(soundUri)
                .setContentTitle("Alarm!!!")
                .setContentText("This is an alarm.")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        Intent resultIntent1 = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent1);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
