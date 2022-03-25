package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.team.database.DatePromiseRepositoryImpl;
import com.example.team.database.TeamDateRepositoryImpl;
import com.example.team.database.TeamRepositoryImpl;
import com.example.team.database.UserRepositoryImpl;
import com.teampp.domain.entities.DatePromise;
import com.teampp.domain.repositories.DatePromiseRepository;
import com.teampp.usecase.ChangeActivity;
import com.example.team.help.NavigationHandler;
import com.teampp.domain.repositories.TeamDateRepository;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.usecase.GetCurrentTeam;
import com.teampp.usecase.DatesOfTeam;
import com.teampp.usecase.InsertDates;

public class HomeActivity extends AppCompatActivity {

    ImageButton teams, home, profile;
    LinearLayout container;
    Button addDate;
    int userID, teamID;
    UserRepositoryImpl userRepository;
    TeamRepositoryImpl teamRepository;
    TeamDateRepositoryImpl teamDateRepository;
    DatePromiseRepositoryImpl datePromiseRepository;
    DatesOfTeam getDatesOfTeamUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        assignElements();
        prepareNavigationBar();
        insertDates();
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
        assignButtonEvents();
    }

    private void insertDates() {
        InsertDates insertDates = new InsertDates(HomeActivity.this, teamDateRepository, datePromiseRepository, teamID, userID,  DateInfoActivity.class);
        insertDates.insertDates(container);
    }

    private void assignButtonEvents() {
        addDate.setOnClickListener(v -> ChangeActivity.changeActivity(HomeActivity.this, CreateTeamDateActivity.class));
    }


    private int getUserID() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPref.getInt("user_id", 0);
    }

    private void assignElements() {
        userRepository = new UserRepositoryImpl();
        teamRepository = new TeamRepositoryImpl();
        teamDateRepository = new TeamDateRepositoryImpl();
        datePromiseRepository = new DatePromiseRepositoryImpl();
        teams = findViewById(R.id.nav_teams);
        home = findViewById(R.id.nav_home);
        profile = findViewById(R.id.nav_profile);
        addDate = findViewById(R.id.home_add_date);
        container = findViewById(R.id.home_date_container);
        userID = getUserID();
        teamID = getCurrentTeamID();
        getDatesOfTeamUseCase = new DatesOfTeam(teamDateRepository, teamID);
    }
}