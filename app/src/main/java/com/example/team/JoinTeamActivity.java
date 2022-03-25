package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

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
import com.teampp.usecase.ChangeActivity;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.domain.repositories.UserTeamConnectionRepository;
import com.teampp.usecase.JoinTeam;

public class JoinTeamActivity extends AppCompatActivity {

    private PinView teamIdView, pin;
    private Button submit;
    private int userID, teamID, teamPin;
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
                clearErrors();
                assignPinAndID();
                if (addUserTeamConnection()) {
                    goToHome();
                }
            }
        });
    }

    private void assignPinAndID() {
        teamPin = Integer.parseInt(pin.getText().toString());
        teamID = Integer.parseInt(teamIdView.getText().toString());
    }


    private void clearErrors() {
        pinError.setText("");
    }

    private boolean addUserTeamConnection() {
        JoinTeam joinTeamUseCase = new JoinTeam(userTeamConnectionRepository, teamRepository, userRepository, pinError);
        return joinTeamUseCase.joinTeam(userID, teamID, teamPin);
    }

    private int getUserID() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPref.getInt("user_id", 0);
    }

    private void assignElements() {
        submit = findViewById(R.id.jointeam_btn);
        teamIdView = findViewById(R.id.jointeam_id);
        pin = findViewById(R.id.jointeam_pin);
        pinError = findViewById(R.id.jointeam_pin_error);
        userTeamConnectionRepository = new UserTeamConnectionRepositoryImpl();
        userRepository = new UserRepositoryImpl();
        teamRepository = new TeamRepositoryImpl();
        userID = getUserID();
    }

    private void goToHome() {
        ChangeActivity.changeActivity(JoinTeamActivity.this, HomeActivity.class);
    }
}