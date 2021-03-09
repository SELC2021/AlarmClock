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
    public void onClick(View v) {
        inputTime = Integer.valueOf(inputTime);

        showText(String.valueOf(inputTime));

    }

    /**
     * Used to create the clock and store the time given by the user
     *
     */
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle saveTime) {

            final Calendar Initial = Calendar.getInstance();
            int hour = Initial.get(Calendar.HOUR_OF_DAY);
            int min = Initial.get(Calendar.MINUTE);

            return new TimePickerDialog((getActivity()),
            this, hour, min, DateFormat.is24HourFormat(getActivity()));

        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar currentTime = Calendar.getInstance();
            currentTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            currentTime.set(Calendar.MINUTE, minute);
            Activity2.alarmTime = currentTime;
        }
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
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

}