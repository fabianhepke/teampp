package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.team.database.TeamRepositoryImpl;
import com.example.team.database.UserRepositoryImpl;
import com.example.team.help.ActivityChanger;
import com.example.team.help.NavigationHandler;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.usecase.GetCurrentTeam;

public class HomeActivity extends AppCompatActivity {

    ImageButton teams, home, profile;
    Button addDate;
    int userID, teamID;
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

    private int getCurrentTeamID() {
        GetCurrentTeam getCurrentTeamUseCase = new GetCurrentTeam(teamRepository, userRepository, userID);
        return getCurrentTeamUseCase.getCurrentTeamID();
    }

    @Override
    protected void onResume() {
        super.onResume();
        insertDates();
        assignButtonEvents();
    }

    private void assignButtonEvents() {
        addDate.setOnClickListener(v -> ActivityChanger.changeActivityTo(HomeActivity.this, CreateTeamDateActivity.class));
    }

    private void insertDates() {

    }

    private int getUserID() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPref.getInt("user_id", 0);
    }

    private void assignElements() {
        teams = findViewById(R.id.nav_teams);
        home = findViewById(R.id.nav_home);
        profile = findViewById(R.id.nav_profile);
        addDate = findViewById(R.id.home_add_date);
        userID = getUserID();
        userRepository = new UserRepositoryImpl();
        teamRepository = new TeamRepositoryImpl();
        teamID = getCurrentTeamID();
    }
}