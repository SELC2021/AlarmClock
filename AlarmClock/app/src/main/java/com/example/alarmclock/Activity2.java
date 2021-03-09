package com.example.alarmclock;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * inputTime recieves the time desired for when the alarm is set
 */
public class Activity2 extends AppCompatActivity {
    int inputTime;
    private Button inpuTime;
    private static Calendar alarmTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


      //  inpuTime = (Button) findViewById(R.id.inputTime);

    }
    public void onClick(View v){
    inputTime = Integer.valueOf(inpuTime.getText().toString());

            showText(String.valueOf(inputTime));

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createAlarm(TimePicker timePicker) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, timePicker.getCurrentHour());
        cal.set(Calendar.MINUTE, timePicker.getCurrentMinute());

        long time = alarmTime.getTimeInMillis();
        Alarm alarm = new Alarm();
        alarm.setAlarm(this, time);
    }

    public void showText(String text){
        Toast.makeText(Activity2.this, text, Toast.LENGTH_SHORT).show();
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new timPickerFragment.TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

}