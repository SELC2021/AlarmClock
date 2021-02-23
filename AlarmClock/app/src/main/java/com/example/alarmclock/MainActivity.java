package com.example.alarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void makeNoise(View view) {
        MediaPlayer mp=new MediaPlayer();
        try{
            mp = MediaPlayer.create(this, R.raw.loud_alarm_clock);
            mp.start();

        }catch(Exception e){e.printStackTrace();}

    }
}
