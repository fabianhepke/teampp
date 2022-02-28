package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageButton;

import com.example.team.help.NavigationHandler;
import com.teampp.domain.entities.User;
import com.teampp.domain.entities.valueobjects.BasicID;

public class ProfileActivity extends AppCompatActivity {
    ImageButton teams, home, profile;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        assignElements();
        getUserInfos();

        NavigationHandler nav = new NavigationHandler(this);
        nav.setNavigaionBarColor(teams, home, profile, 3);
        nav.addNavigationBarEvents(teams, home, profile);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private User getUserInfos() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return new User(new BasicID(sharedPref.getInt("user_id", 0)));
    }

    private void assignElements() {
        teams = findViewById(R.id.nav_teams);
        home = findViewById(R.id.nav_home);
        profile = findViewById(R.id.nav_profile);
    }
}