package com.teampp.plugin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.team.R;
import com.teampp.plugin.database.UserRepositoryImpl;
import com.teampp.plugin.help.ChangeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLogin();
    }

    private void checkLogin() {
        String loginToken = getLoginToken();
        int userId = getUserID();
        if (loginToken == null || userId == 0) {
            goToLogin();
            return;
        }
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        if (userRepository.isUserLoggedIn(userId, loginToken)) {
            goToHome();
            return;
        }
        goToJoinOrCreateTeam();
    }

    private void goToJoinOrCreateTeam() {
        ChangeActivity.changeActivity(this, JoinOrCreateTeamActivity.class);
    }

    private void goToHome() {
        ChangeActivity.changeActivity(MainActivity.this, HomeActivity.class);
    }

    private int getUserID() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPref.getInt("user_id", 0);
    }

    private void goToLogin() {
        ChangeActivity.changeActivity(MainActivity.this, LoginActivity.class);
    }

    private String getLoginToken() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPref.getString("login_token", null);
    }
}