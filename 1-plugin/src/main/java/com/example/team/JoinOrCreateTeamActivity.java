package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.team.help.ChangeActivity;

public class JoinOrCreateTeamActivity extends AppCompatActivity {

    Button join, create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_or_create_team);
        assignElements();
    }

    private void assignElements() {
        join = findViewById(R.id.joinorcreate_join);
        create = findViewById(R.id.joinorcreate_create);
    }

    @Override
    protected void onResume() {
        super.onResume();
        assignButtonEvents();
    }

    private void assignButtonEvents() {
        join.setOnClickListener(v -> ChangeActivity.changeActivity(JoinOrCreateTeamActivity.this, JoinTeamActivity.class));

        create.setOnClickListener(v -> ChangeActivity.changeActivity(JoinOrCreateTeamActivity.this, CreateTeamActivity.class));
    }
}