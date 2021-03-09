package com.example.alarmclock;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createAlarm(long time) {
        Alarm alarm = new Alarm();
        alarm.setAlarm(this, time);
    }

    public void makeNoise(View view)
    {
        try {
            mp = MediaPlayer.create(this, R.raw.loud_alarm_clock);
            mp.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void stopNoise(View view) {
        mp.release();
    }
}
