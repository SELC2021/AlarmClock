package com.example.alarmclock;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

/**
 * inputTime recieves the time desired for when the alarm is set
 */
public class Activity2 extends AppCompatActivity {

    DialogFragment newFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        //  inpuTime = (Button) findViewById(R.id.inputTime);

    }

    public void onClick(View v) {

    }

    public void showText(String text) {
        Toast.makeText(Activity2.this, text, Toast.LENGTH_SHORT).show();
    }

    public void showTimePickerDialog(View v) {
        newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

}