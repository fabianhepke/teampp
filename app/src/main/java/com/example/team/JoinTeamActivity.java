package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chaos.view.PinView;
import com.example.team.database.TeamRepositoryImpl;
import com.example.team.database.UserRepositoryImpl;
import com.example.team.database.UserTeamConnectionRepositoryImpl;
import com.example.team.help.ActivityChanger;
import com.teampp.domain.entities.*;
import com.teampp.domain.entities.enums.Rank;
import com.teampp.domain.entities.valueobjects.BasicID;
import com.teampp.domain.entities.valueobjects.TeamID;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.domain.repositories.UserTeamConnectionRepository;
import com.teampp.usecase.JoinTeam;

public class JoinTeamActivity extends AppCompatActivity {

    private PinView teamId, pin;
    private Button submit;
    private User user;
    private Team team;
    private TextView pinError;
    private UserTeamConnectionRepository userTeamConnectionRepository;
    private TeamRepository teamRepository;
    private UserRepository userRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_team);


        assignElements();
    }

    @Override
    protected void onResume() {
        super.onResume();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTeam();
                clearErrors();
                addUserTeamConnection();
                goToHome();
            }
        });
    }

    private void createTeam() {
        team = new Team(new TeamID(Integer.parseInt(teamId.getText().toString())), Integer.parseInt(pin.getText().toString()));
    }


    private void clearErrors() {
        pinError.setText("");
    }

    private void addUserTeamConnection() {
        JoinTeam joinTeamUseCase = new JoinTeam(userTeamConnectionRepository, teamRepository, userRepository, pinError);
        joinTeamUseCase.joinTeam(user, team);
    }

    private User getUserInfos() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        User tmpUser = new User(new BasicID(sharedPref.getInt("user_id", 0)));
        tmpUser.setRank(Rank.PLAYER);
        return tmpUser;
    }

    private void assignElements() {
        submit = findViewById(R.id.jointeam_btn);
        teamId = findViewById(R.id.jointeam_id);
        pin = findViewById(R.id.jointeam_pin);
        pinError = findViewById(R.id.jointeam_pin_error);
        userTeamConnectionRepository = new UserTeamConnectionRepositoryImpl();
        userRepository = new UserRepositoryImpl();
        teamRepository = new TeamRepositoryImpl();
        user = getUserInfos();
    }

    private void goToHome() {
        ActivityChanger.changeActivityTo(JoinTeamActivity.this, HomeActivity.class);
    }
}