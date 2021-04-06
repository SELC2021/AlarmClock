package com.example.alarmclock;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

public class Alarm extends BroadcastReceiver {
    private final String CHANNEL_ID = "";
    private PendingIntent pi;
    private MediaPlayer mp;
    private Uri uriSound;



    @Override
    public void onReceive(Context context, Intent intent) {
        //MainActivity.openSnoozeDismissPage();
        Intent notificationIntent = new Intent(context,dismiss_snooze.class);
        pi = PendingIntent.getActivity(context, 0, notificationIntent, 0);


        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "");
        wl.acquire();

        Toast.makeText(context, "Alarm", Toast.LENGTH_LONG).show(); // For example

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "alarmChannelID")
                .setContentTitle("Alarm!!!")
                .setContentText("This is an alarm.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pi);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        Random notification_id = new Random();
        notificationManager.notify(notification_id.nextInt(100), builder.build());

        makeNoise(context);

        wl.release();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setAlarm(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 1, pi); // 1000 * 60 * minute interval

        Toast toast = Toast.makeText(context, "Alarm", Toast.LENGTH_LONG);
        toast.show();
    }

    public void cancelAlarm(Context context) {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }


    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "";
            String description = "";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void makeNoise(Context context) {
        try {
            System.out.println(uriSound);
            mp = new MediaPlayer();
            if(uriSound!=null) {
                mp.setDataSource(context, uriSound);
                mp.setVolume(1,1);
                mp.prepare();


            }else {
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
}
