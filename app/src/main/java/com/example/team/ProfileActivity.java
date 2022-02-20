package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.team.components.User;
import com.example.team.help.NavigationHandler;

public class ProfileActivity extends AppCompatActivity {
    ImageButton teams, home, profile;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        assignElements();
        getUserInfos();

        NavigationHandler nav = new NavigationHandler(this, user.getUserID());
        nav.setNavigaionBarColor(teams, home, profile, 3);
        nav.addNavigationBarEvents(teams, home, profile);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getUserInfos() {
        Bundle extra = getIntent().getExtras();
        user.setUserID(extra.getInt("user_id"));
    }

    private void assignElements() {
        teams = findViewById(R.id.nav_teams);
        home = findViewById(R.id.nav_home);
        profile = findViewById(R.id.nav_profile);
    }
}