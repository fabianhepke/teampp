package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.team.components.User;

public class JoinOrCreateTeamActivity extends AppCompatActivity {

    Button join, create;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_or_create_team);
        assignElements();
        getUserInfos();
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
                Intent intent = new Intent(JoinOrCreateTeamActivity.this, JoinTeamActivity.class);
                intent.putExtra("user_id", user.getUserID());
                startActivity(intent);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinOrCreateTeamActivity.this, CreateTeamActivity.class);
                intent.putExtra("user_id", user.getUserID());
                startActivity(intent);
            }
        });
    }

    private void getUserInfos() {
        Bundle extra = getIntent().getExtras();
        user.setUserID(extra.getInt("user_id"));
    }
}