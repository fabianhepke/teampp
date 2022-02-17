package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.team.components.Rank;
import com.example.team.components.Team;
import com.example.team.components.User;
import com.example.team.database.PhpConnection;

public class CreateTeamActivity extends AppCompatActivity {

    EditText teamnameInput, descriptionInput, teamPin;
    TextView teamIDView;
    Button submitBtn;
    Team team;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        getUserInfos();
        assignElements();
        createNewTeam();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showTeamId();
        assignButtonEvents();
    }

    private void showTeamId() {
        teamIDView.setText(String.valueOf(team.getTeamID().getCode()));
    }

    private void getUserInfos() {
        Bundle extra = getIntent().getExtras();
        user.setUserID(extra.getInt("user_id"));
    }

    private void assignElements() {
        teamPin = findViewById(R.id.creatteam_pin);
        teamnameInput = findViewById(R.id.creatteam_teamname);
        submitBtn = findViewById(R.id.createteam_submit);
        descriptionInput = findViewById(R.id.creatteam_description);
        teamIDView = findViewById(R.id.createteam_id);
    }

    private void createNewTeam() {
        team = new Team();
    }

    private void assignButtonEvents() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInfosToTeam();
                saveTeamInDatabase();
                saveUserTeamConnection();
                goToHome();
            }
        });
    }

    private void saveUserTeamConnection() {
        PhpConnection conn = new PhpConnection();
        conn.addConnection(user.getUserID(), team.getTeamID().getCode(), Rank.PLAYERADMIN);
    }

    private void addInfosToTeam() {
        team.setDescription(descriptionInput.getText().toString());
        team.setTeamName(teamnameInput.getText().toString());
        team.setPin(Integer.parseInt(teamPin.getText().toString()));
    }

    private void saveTeamInDatabase() {
        PhpConnection conn = new PhpConnection();
        conn.registerTeam(team);
    }

    private void goToHome() {
        Intent intent = new Intent(CreateTeamActivity.this, HomeActivity.class);
        intent.putExtra("user_id", user.getUserID());
        startActivity(intent);
    }
}