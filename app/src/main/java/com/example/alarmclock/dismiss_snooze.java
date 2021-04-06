package com.example.alarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class dismiss_snooze extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_dismiss_snooze);



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


            @Override
            public void onClick(View v) {
                //snooze
            }

        });
    }
}

