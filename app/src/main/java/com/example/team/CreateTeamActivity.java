package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.team.database.TeamRepositoryImpl;
import com.example.team.database.UserRepositoryImpl;
import com.example.team.database.UserTeamConnectionRepositoryImpl;
import com.teampp.domain.entities.*;
import com.teampp.domain.entities.enums.Rank;
import com.teampp.domain.valueobjects.*;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.domain.repositories.UserTeamConnectionRepository;
import com.teampp.usecase.CreateTeam;
import com.teampp.usecase.GetCurrentTeam;

public class CreateTeamActivity extends AppCompatActivity {

    private EditText teamnameInput, descriptionInput, teamPin;
    private TextView teamIDView;
    private Button submitBtn;
    private int teamID;
    private int userID;
    private UserRepository userRepository;
    private TeamRepository teamRepository;
    private UserTeamConnectionRepository userTeamConnectionRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        assignElements();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showTeamId();
        assignButtonEvents();
    }

    private void showTeamId() {
        teamIDView.setText(new CreateTeam(teamRepository, userRepository, userTeamConnectionRepository).getNewTeamID());
    }

    private int getUserID() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPref.getInt("user_id", 0);
    }

    private void assignElements() {
        userRepository = new UserRepositoryImpl();
        teamRepository = new TeamRepositoryImpl();
        userTeamConnectionRepository = new UserTeamConnectionRepositoryImpl();
        teamPin = findViewById(R.id.creatteam_pin);
        teamnameInput = findViewById(R.id.creatteam_teamname);
        submitBtn = findViewById(R.id.createteam_submit);
        descriptionInput = findViewById(R.id.creatteam_description);
        teamIDView = findViewById(R.id.createteam_id);
        userID = getUserID();
        teamID = getTeamID();
    }

    private int getTeamID() {
        GetCurrentTeam getCurrentTeamUseCase = new GetCurrentTeam(teamRepository, userRepository, userID);
        return getCurrentTeamUseCase.getCurrentTeamID();
    }

    private void assignButtonEvents() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTeamInDatabase();
                goToHome();
            }
        });
    }


    private void saveTeamInDatabase() {
        CreateTeam createTeamUseCase = new CreateTeam(teamRepository, userRepository, userTeamConnectionRepository);
        createTeamUseCase.createTeam(teamID, teamnameInput.getText().toString(), descriptionInput.getText().toString(), Integer.parseInt(teamPin.getText().toString()),userID);
    }

    private void goToHome() {
        Intent intent = new Intent(CreateTeamActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}