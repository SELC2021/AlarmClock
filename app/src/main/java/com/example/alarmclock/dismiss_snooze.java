package com.example.alarmclock;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class dismiss_snooze extends AppCompatActivity {
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_dismiss_snooze);

        context = this;

        //  inputTime = (Button) findViewById(R.id.inputTime);
        Button snooze = (Button) this.findViewById(R.id.snooze);
        snooze.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intentService = new Intent(getApplicationContext(), Alarm.class);
                getApplicationContext().stopService(intentService);
                finish();
            }

        });

        //  inputTime = (Button) findViewById(R.id.inputTime);
        Button dismiss = (Button) this.findViewById(R.id.dismiss);
        snooze.setOnClickListener(new View.OnClickListener() {


            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                //snooze
                Alarm alarm = new Alarm();
                Calendar alarmTime = Calendar.getInstance();
                long time = alarmTime.getTimeInMillis();
                time += 1000*60*10;//millis to sec,sec to minute, 10 minutes
                alarm.setAlarm(context,time,10);
            }

        });
    }
}

