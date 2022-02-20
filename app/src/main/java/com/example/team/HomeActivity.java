package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.team.components.Team;
import com.example.team.components.TeamCode;
import com.example.team.components.User;
import com.example.team.help.NavigationHandler;

public class HomeActivity extends AppCompatActivity {

    ImageButton teams, home, profile;
    User user = new User();
    Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        assignElements();
        getUserInfos();
        checkTeamInfos();

        NavigationHandler nav = new NavigationHandler(this, user.getUserID());
        nav.setNavigaionBarColor(teams, home, profile, 2);
        nav.addNavigationBarEvents(teams, home, profile);
    }

    private void checkTeamInfos() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        user.setUserID(sharedPref.getInt("team_id", 100000));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getUserInfos() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        user.setUserID(sharedPref.getInt("user_id", 0));
    }

    private void getTeamInfos() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        team = new Team(new TeamCode(sharedPref.getInt("team_id", 100000)));
    }

    private void assignElements() {
        teams = findViewById(R.id.nav_teams);
        home = findViewById(R.id.nav_home);
        profile = findViewById(R.id.nav_profile);
    }
}