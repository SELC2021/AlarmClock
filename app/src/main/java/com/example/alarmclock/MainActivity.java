package com.example.alarmclock;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mp;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goToAlarmPage = (Button) findViewById(R.id.alarmSet);
        goToAlarmPage.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //makeNoise(v);
                openActivity2();
            }

        });
        Button butt = (Button) findViewById(R.id.bigTopcan);
        butt.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //makeNoise(v);
                openSnoozeDismissPage();
                System.out.println("snooze page button");
            }

        });
        Alarm alarm = new Alarm();
        alarm.setAlarm(this);

        Button openFile = (Button) this.findViewById(R.id.changeSound);
        openFile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT,null);
                intent.setType("audio/*");
                startActivityForResult(intent, 10);
            }

        });

    }
    public void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }
    public void openSnoozeDismissPage() {
        Intent intent = new Intent(this, dismiss_snooze.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 10) {
            ((application)getApplication()).urisound = data.getData();
        }
    }

    public void makeNoise(View view) {
        Uri uriSound = ((application)getApplication()).urisound;
        try {
            System.out.println(uriSound);
            mp = new MediaPlayer();
            if(uriSound!=null) {
                mp.setDataSource(this, uriSound);
                mp.setVolume(1,1);
                mp.prepare();


            }else {
                mp = MediaPlayer.create(this, R.raw.loud_alarm_clock);
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