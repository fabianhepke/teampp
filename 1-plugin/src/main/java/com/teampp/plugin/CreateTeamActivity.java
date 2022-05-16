package com.teampp.plugin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.team.R;
import com.teampp.plugin.database.TeamRepositoryImpl;
import com.teampp.plugin.database.UserRepositoryImpl;
import com.teampp.plugin.database.UserTeamConnectionRepositoryImpl;
import com.teampp.plugin.help.ErrorHelper;
import com.teampp.plugin.help.InputChecker;
import com.teampp.domain.team.TeamRepository;
import com.teampp.domain.user.UserRepository;
import com.teampp.domain.userteamconnection.UserTeamConnectionRepository;
import com.teampp.usecase.team.CreateTeam;

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
        teamID = new CreateTeam(teamRepository, userRepository, userTeamConnectionRepository).getNewTeamID();
        teamIDView.setText(String.valueOf(teamID));
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
    }

    private void assignButtonEvents() {
        submitBtn.setOnClickListener(v -> saveTeamInDatabase());
    }


    private void saveTeamInDatabase() {
        if (InputChecker.isCreateTeamCorrect(teamnameInput, descriptionInput, teamPin)) {
            CreateTeam createTeamUseCase = new CreateTeam(teamRepository, userRepository, userTeamConnectionRepository);
            createTeamUseCase.createTeam(teamID, teamnameInput.getText().toString(), descriptionInput.getText().toString(), Integer.parseInt(teamPin.getText().toString()),userID);
            goToHome();
        }
        else {
            ErrorHelper.setCreateTeamError(teamnameInput, descriptionInput, teamPin);
        }
    }

    private void goToHome() {
        Intent intent = new Intent(CreateTeamActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}