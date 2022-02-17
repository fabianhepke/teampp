package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chaos.view.PinView;
import com.example.team.components.Rank;
import com.example.team.components.User;
import com.example.team.database.PhpConnection;

public class JoinTeamActivity extends AppCompatActivity {

    PinView teamId, pin;
    Button submit;
    User user = new User();
    TextView pinError, teamIdError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_team);

        getUserInfos();
        assignElements();
    }

    @Override
    protected void onResume() {
        super.onResume();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearErrors();
                if (!doesPinMatchTeam()) {
                    pinError.setText("PIN stimmt nicht mit der Team-ID überein!");
                    return;
                }
                if (doesUserHasConnection()) {
                    teamIdError.setText("Sie sind bereits Mitglied des Teams!");
                    return;
                }
                addUserTeamConnection();
                goToHome();
            }
        });
    }

    private void clearErrors() {
        teamIdError.setText("");
        pinError.setText("");
    }

    private boolean doesUserHasConnection() {
        PhpConnection conn = new PhpConnection();
        return conn.doesUserHasTeamConnection(user.getUserID(), Integer.parseInt(teamId.getText().toString()));
    }

    private boolean doesPinMatchTeam() {
        PhpConnection conn = new PhpConnection();
        return conn.doesPinMatchTeam(teamId.getText().toString(), pin.getText().toString());
    }

    private void addUserTeamConnection() {
        PhpConnection conn = new PhpConnection();
        conn.addConnection(user.getUserID(), Integer.parseInt(teamId.getText().toString()), Rank.PLAYER);
    }

    private void getUserInfos() {
        Bundle extra = getIntent().getExtras();
        user.setUserID(extra.getInt("user_id"));
    }

    private void assignElements() {
        submit = findViewById(R.id.jointeam_btn);
        teamId = findViewById(R.id.jointeam_id);
        pin = findViewById(R.id.jointeam_pin);
        teamIdError = findViewById(R.id.jointeam_teamid_error);
        pinError = findViewById(R.id.jointeam_pin_error);
    }

    private void goToHome() {
        Intent intent = new Intent(JoinTeamActivity.this, HomeActivity.class);
        intent.putExtra("user_id", user.getUserID());
        startActivity(intent);
    }
}