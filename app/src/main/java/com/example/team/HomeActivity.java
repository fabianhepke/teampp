package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.team.database.TeamRepositoryImpl;
import com.example.team.database.UserRepositoryImpl;
import com.example.team.help.ActivityChanger;
import com.example.team.help.NavigationHandler;
import com.teampp.domain.entities.*;
import com.teampp.domain.entities.valueobjects.BasicID;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.repositories.UserRepository;

public class HomeActivity extends AppCompatActivity {

    ImageButton teams, home, profile;
    Button addDate;
    User user;
    Team team;
    UserRepository userRepository;
    TeamRepository teamRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        assignElements();
        prepareNavigationBar();
    }

    private void prepareNavigationBar() {
        NavigationHandler nav = new NavigationHandler(this);
        nav.setNavigaionBarColor(teams, home, profile, 2);
        nav.addNavigationBarEvents(teams, home, profile);
    }

    private Team getCurrentTeam() {
        return userRepository.getCurrentTeam(user);
    }

    @Override
    protected void onResume() {
        super.onResume();
        insertDates();
        assignButtonEvents();
    }

    private void assignButtonEvents() {
        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityChanger.changeActivityTo(HomeActivity.this, CreateTeamDateActivity.class);
            }
        });
    }

    private void insertDates() {

    }

    private User getUser() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return new User(new BasicID(sharedPref.getInt("user_id", 0)));
    }

    private void assignElements() {
        teams = findViewById(R.id.nav_teams);
        home = findViewById(R.id.nav_home);
        profile = findViewById(R.id.nav_profile);
        addDate = findViewById(R.id.home_add_date);
        user = getUser();
        userRepository = new UserRepositoryImpl();
        teamRepository = new TeamRepositoryImpl();
        team = getCurrentTeam();
    }
}