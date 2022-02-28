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
import com.teampp.domain.entities.valueobjects.*;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.domain.repositories.UserTeamConnectionRepository;
import com.teampp.usecase.CreateTeam;

public class CreateTeamActivity extends AppCompatActivity {

    private EditText teamnameInput, descriptionInput, teamPin;
    private TextView teamIDView;
    private Button submitBtn;
    private Team team;
    private User user;
    private UserRepository userRepository;
    private TeamRepository teamRepository;
    private UserTeamConnectionRepository userTeamConnectionRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        getUserInfos();
        assignElements();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showTeamId();
        assignButtonEvents();
    }

    private void showTeamId() {
        teamIDView.setText(String.valueOf(team.getTeamID().toInt()));
    }

    private User getUserInfos() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        User tmpUser = new User(new BasicID(sharedPref.getInt("user_id", 0)));
        tmpUser.setRank(Rank.PLAYERADMIN);
        return tmpUser;
    }

    private void assignElements() {
        teamPin = findViewById(R.id.creatteam_pin);
        teamnameInput = findViewById(R.id.creatteam_teamname);
        submitBtn = findViewById(R.id.createteam_submit);
        descriptionInput = findViewById(R.id.creatteam_description);
        teamIDView = findViewById(R.id.createteam_id);
        userRepository = new UserRepositoryImpl();
        teamRepository = new TeamRepositoryImpl();
        userTeamConnectionRepository = new UserTeamConnectionRepositoryImpl();
        team = new Team(new TeamID(teamRepository.getNewTeamID()));
        user = getUserInfos();
    }

    private void assignButtonEvents() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInfosToTeam();
                saveTeamInDatabase();
                goToHome();
            }
        });
    }

    private void addInfosToTeam() {
        team.setDescription(descriptionInput.getText().toString());
        team.setTeamName(teamnameInput.getText().toString());
        team.setPin(Integer.parseInt(teamPin.getText().toString()));
    }

    private void saveTeamInDatabase() {
        CreateTeam createTeamUseCase = new CreateTeam(teamRepository, userRepository, userTeamConnectionRepository);
        createTeamUseCase.createTeam(team, user);
    }

    private void goToHome() {
        Intent intent = new Intent(CreateTeamActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}