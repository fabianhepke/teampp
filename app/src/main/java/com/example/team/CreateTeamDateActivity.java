package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.team.database.TeamDateRepositoryImpl;
import com.example.team.database.UserRepositoryImpl;
import com.example.team.help.DateConverter;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.teampp.domain.entities.TeamDate;
import com.teampp.domain.entities.User;
import com.teampp.domain.entities.valueobjects.BasicID;
import com.teampp.domain.repositories.TeamDateRepository;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.usecase.CreateTeamDate;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CreateTeamDateActivity extends AppCompatActivity {

    private TeamDate date;
    private RadioGroup rg;
    private RadioButton home, abroad;
    private EditText title, plz, place, street, hnr;
    private Button addDate, addTime, submit;
    private TextView timeView, dateView;
    private boolean dateSet = false, timeSet = false;
    private TeamDateRepository teamDateRepository;
    private UserRepository userRepository;
    private int day, year, month, min, hour;
    private Date viewDate;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team_date);

        assignElements();
        addTeamID();
    }

    private void addTeamID() {
        date.setTeamID(userRepository.getCurrentTeam(user).getTeamID());
    }

    private void assignElements() {
        teamDateRepository = new TeamDateRepositoryImpl();
        userRepository = new UserRepositoryImpl();
        addDate = findViewById(R.id.date_choose_date);
        addTime = findViewById(R.id.date_choose_time);
        timeView = findViewById(R.id.date_time_text);
        dateView = findViewById(R.id.date_date_text);
        submit = findViewById(R.id.date_submit_date);
        title = findViewById(R.id.date_titel_input);
        plz = findViewById(R.id.date_plz_input);
        street = findViewById(R.id.date_street_input);
        place = findViewById(R.id.date_place_input);
        hnr = findViewById(R.id.date_hnr_input);
        home = findViewById(R.id.date_radio_daheim);
        abroad = findViewById(R.id.date_radio_auswärts);
        rg = findViewById(R.id.date_radio_groupe);
        date = new TeamDate();
        user = getUser();
    }

    private User getUser() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return new User(new BasicID(sharedPref.getInt("user_id", 0)));
    }

    @Override
    protected void onResume() {
        super.onResume();

        assignButtonEvents();
        setRadioEvents();
    }

    private void setRadioEvents() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (home.isChecked()) {
                    plz.setEnabled(false);
                    place.setEnabled(false);
                    street.setEnabled(false);
                    hnr.setEnabled(false);
                    plz.setBackgroundColor(getResources().getColor(R.color.mid_grey));
                    place.setBackgroundColor(getResources().getColor(R.color.mid_grey));
                    street.setBackgroundColor(getResources().getColor(R.color.mid_grey));
                    hnr.setBackgroundColor(getResources().getColor(R.color.mid_grey));
                }
                else {
                    plz.setEnabled(true);
                    place.setEnabled(true);
                    street.setEnabled(true);
                    hnr.setEnabled(true);
                    plz.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    place.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    street.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    hnr.setBackgroundColor(getResources().getColor(R.color.light_grey));
                }
            }
        });
    }

    private void assignButtonEvents() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewDate();
            }
        });

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

    private void createNewDate() {
        if (!timeAndDateIsSet()) {
            return;
        }
        CreateTeamDate createTeamDateUseCase = new CreateTeamDate(teamDateRepository);
        String dateTime = DateConverter.convertDateToString(viewDate);
        if (home.isChecked()) {
            createTeamDateUseCase.createHomeTeamDate(date, dateTime, title);
        }
        else {
            createTeamDateUseCase.createTeamDate(date, dateTime, title, plz, place, street, hnr);

        }
    }

    private boolean timeAndDateIsSet() {
        boolean result = true;
        if (!timeSet) {
            timeView.setText("Zeit wählen!");
            result = false;
        }
        if (!dateSet) {
            dateView.setText("Datum wählen!");
            result = false;
        }
        return result;
    }

    private void setTime() {
        MaterialTimePicker picker = new MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .build();
        picker.show(getSupportFragmentManager(), picker.toString());
        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour = picker.getHour();
                min = picker.getMinute();
                timeView.setText(picker.getHour() + ":" + picker.getMinute() + " Uhr");
                date.setDate(new Date(date.getDate().getTime() + picker.getMinute() * 60000 + picker.getHour() * 3600000));
                timeSet = true;
                addTime.setEnabled(false);
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
                viewDate = new Date(selection);
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
                cal.setTime(viewDate);
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);
                dateView.setText(day + "." + (month+1) + "." + year);
                date.setDate(viewDate);
                dateSet = true;
                addDate.setEnabled(false);
            }
        });
    }
}