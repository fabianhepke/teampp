package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.team.database.TeamDateRepositoryImpl;
import com.example.team.database.UserRepositoryImpl;
import com.example.team.help.InsertUsersOfDate;

public class DateInfoActivity extends AppCompatActivity {

    private LinearLayout promiseContainer, cancelContainer;
    private TextView dateTitleTextView;
    private int dateID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_info);

        assignElements();
        insertData();
        insertDateTitle();
    }

    private void insertDateTitle() {
        TeamDateRepositoryImpl teamDateRepository = new TeamDateRepositoryImpl();
        dateTitleTextView.setText(teamDateRepository.getDatenameByID(dateID));

    }

    private void insertData() {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        InsertUsersOfDate insertUsersOfDate = new InsertUsersOfDate(this, userRepository, dateID);
        insertUsersOfDate.insertPromisedUsers(promiseContainer);
        insertUsersOfDate.insertCanceledUsers(cancelContainer);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void assignElements() {
        promiseContainer = findViewById(R.id.info_zusagen_container);
        cancelContainer = findViewById(R.id.info_absagen_container);
        dateTitleTextView = findViewById(R.id.info_datetitle);
        assignDateID();
    }

    private void assignDateID() {
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            dateID =extras.getInt("date_id");
            return;
        }
        dateID = 0;
    }
}