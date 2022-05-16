package com.teampp.plugin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;

import com.chaos.view.PinView;
import com.example.team.R;
import com.teampp.plugin.database.TeamRepositoryImpl;
import com.teampp.plugin.database.UserRepositoryImpl;
import com.teampp.plugin.database.UserTeamConnectionRepositoryImpl;
import com.teampp.plugin.help.ChangeActivity;
import com.teampp.plugin.help.ErrorHelper;
import com.teampp.plugin.help.InputChecker;
import com.teampp.domain.team.TeamRepository;
import com.teampp.domain.user.UserRepository;
import com.teampp.domain.userteamconnection.UserTeamConnectionRepository;
import com.teampp.usecase.team.JoinTeam;

public class JoinTeamActivity extends AppCompatActivity {

    private PinView teamIdView, pinView;
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

        submit.setOnClickListener(v -> {
            clearErrors();
            assignPinAndID();
            addUserTeamConnection();
        });
    }

    private void assignPinAndID() {
        if (!pinView.getText().toString().equals("") && !teamIdView.getText().toString().equals("")) {
            teamPin = Integer.parseInt(pinView.getText().toString());
            teamID = Integer.parseInt(teamIdView.getText().toString());
        }
        else {
            pinError.setText("Alle Felder müssen ausgefüllt werden");
        }
    }


    private void clearErrors() {
        pinError.setText("");
    }

    private void addUserTeamConnection() {
        if (!InputChecker.isJoinTeamCorrect(userID, teamID, teamPin)) {
            ErrorHelper.setJoinTeamError(userID, teamID, teamPin, pinError);
            return;
        }
        JoinTeam joinTeamUseCase = new JoinTeam(userTeamConnectionRepository, teamRepository, userRepository);
        joinTeamUseCase.joinTeam(userID, teamID, teamPin);
        goToHome();
    }

    private int getUserID() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPref.getInt("user_id", 0);
    }

    private void assignElements() {
        submit = findViewById(R.id.jointeam_btn);
        teamIdView = findViewById(R.id.jointeam_id);
        pinView = findViewById(R.id.jointeam_pin);
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