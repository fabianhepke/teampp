package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.team.database.DatePromiseRepositoryImpl;
import com.example.team.database.TeamDateRepositoryImpl;
import com.example.team.database.TeamRepositoryImpl;
import com.example.team.database.UserRepositoryImpl;
import com.example.team.help.ChangeActivity;
import com.example.team.help.NavigationHandler;
import com.teampp.usecase.team.GetCurrentTeam;
import com.teampp.usecase.teamdate.DatesOfTeam;
import com.example.team.help.InsertDates;

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
        adjustScrollView();
    }

    private void adjustScrollView() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        findViewById(R.id.home_scrollview).getLayoutParams().height = height - 900;
        findViewById(R.id.home_scrollview).requestLayout();
    }

    private void insertDates() {
        InsertDates insertDates = new InsertDates(HomeActivity.this, teamDateRepository, datePromiseRepository, teamID, userID,  DateInfoActivity.class, HomeActivity.class);
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