package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.team.components.Team;

public class CreateTeamActivity extends AppCompatActivity {

    EditText teamnameInput;
    Button submitBtn;
    Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        teamnameInput = findViewById(R.id.creatteam_teamname);
        submitBtn = findViewById(R.id.createteam_submit);
        team = new Team();
    }

    @Override
    protected void onResume() {
        super.onResume();
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateTeamActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}