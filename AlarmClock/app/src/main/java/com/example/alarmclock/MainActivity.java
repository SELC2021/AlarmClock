package com.example.alarmclock;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button goToAlarmPage;
    private MediaPlayer mp;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToAlarmPage = (Button) findViewById(R.id.alarmSet);
        goToAlarmPage.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                openActivity2();
            }

        });

        Alarm alarm = new Alarm();
        alarm.setAlarm(this);
        //alarm.onReceive(this, getIntent());

    }

    public void openActivity2(){
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
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