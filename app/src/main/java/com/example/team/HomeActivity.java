package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.team.components.User;
import com.example.team.help.NavigationHandler;

public class HomeActivity extends AppCompatActivity {

    LinearLayout teams, home, profile;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        assignElements();
        getUserInfos();

        NavigationHandler nav = new NavigationHandler(this, user.getUserID());
        nav.setNavigaionBarColor(teams, home, profile, 2);
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