package com.example.alarmclock;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private Calendar alarmTime;

    @Override
    public Dialog onCreateDialog(Bundle saveTime) {

        final Calendar Initial = Calendar.getInstance();
        int hour = Initial.get(Calendar.HOUR_OF_DAY);
        int min = Initial.get(Calendar.MINUTE);

        return new TimePickerDialog((getActivity()),
                this, hour, min, DateFormat.is24HourFormat(getActivity()));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        alarmTime = Calendar.getInstance();
        alarmTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        alarmTime.set(Calendar.MINUTE, minute);
        alarmTime.set(Calendar.SECOND, 0);

        long time = alarmTime.getTimeInMillis();
        Alarm alarm = new Alarm();
        alarm.setAlarm(getActivity().getApplicationContext(), time);
    }

    public void onClick(View v) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(alarmTime.toString()));
        intent.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(alarmTime.toString()));
    }

}
