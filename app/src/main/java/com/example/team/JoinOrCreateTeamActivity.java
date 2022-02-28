package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.team.help.ActivityChanger;
import com.teampp.domain.entities.User;

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
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityChanger.changeActivityTo(JoinOrCreateTeamActivity.this, JoinTeamActivity.class);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityChanger.changeActivityTo(JoinOrCreateTeamActivity.this, CreateTeamActivity.class);
            }
        });
    }
}