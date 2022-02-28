package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.teampp.domain.entities.TeamDate;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static android.content.ContentValues.TAG;

public class CreateTeamDateActivity extends AppCompatActivity {

    TeamDate date;
    Button addDate, addTime;
    TextView timeView, dateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team_date);

        addDate = findViewById(R.id.date_choose_date);
        addTime = findViewById(R.id.date_choose_time);
        timeView = findViewById(R.id.date_time_text);
        dateView = findViewById(R.id.date_date_text);
        date = new TeamDate();

    }

    @Override
    protected void onResume() {
        super.onResume();

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });

        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();
            }
        });
    }

    private void setTime() {
        MaterialTimePicker picker = new MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(10)
                .build();
        picker.show(getSupportFragmentManager(), picker.toString());
        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeView.setText(picker.getHour() + ":" + picker.getMinute() + " Uhr");
                date.setDate(new Date(date.getDate().getTime() + picker.getMinute() * 60000 + picker.getHour() * 3600000));
            }
        });
    }

    private void setDate() {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Datum auswählen");
        MaterialDatePicker<Long> picker = builder.build();
        picker.show(getSupportFragmentManager(), picker.toString());
        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                Date viewDate = new Date(selection);
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
                cal.setTime(viewDate);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                dateView.setText(day + "." + (month+1) + "." + year);
                date.setDate(viewDate);
            }
        });
    }
}