package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.team.components.Team;
import com.example.team.database.PhpConnection;

public class CreateTeamActivity extends AppCompatActivity {

    EditText teamnameInput, descriptionInput, teamPin;
    TextView teamIDView;
    Button submitBtn;
    Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        assignElements();
        createNewTeam();
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

    @Override
    protected void onResume() {
        super.onResume();
        teamIDView.setText(String.valueOf(team.getTeamID().getCode()));
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
        PhpConnection conn = new PhpConnection();
        conn.registerTeam(team);
    }

    private void goToHome() {
        Intent intent = new Intent(CreateTeamActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}