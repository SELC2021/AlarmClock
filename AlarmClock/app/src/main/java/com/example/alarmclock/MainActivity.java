package com.example.alarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button goToAlarmPage;
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

    }
    public void openActivity2(){
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }



}