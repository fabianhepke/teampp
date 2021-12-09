package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.team.components.User;
import com.example.team.database.PhpConnection;
import com.example.team.help.EMail;
import com.example.team.help.InputChecker;
import com.example.team.help.ToastMessageHelper;

import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button login, register;
    private CheckBox stayLoggedInBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        assignElements();
    }


    @Override
    protected void onResume() {
        super.onResume();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user;
                if (InputChecker.checkLoginData(LoginActivity.this, username.getText().toString(), password.getText().toString())) {

                }
                user = getLoginUser(username.getText().toString(), password.getText().toString(), stayLoggedInBox.isChecked());

            }
        });
    }

    private void handleLogin(String usernaameOrEmail) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

            }
        }, 2000);
    }

    private void goToHomeOrJoinTeam(User user) {
        if (user.getTeamID() == null) {
            goToJoinOrCreateTeam();
        }
        goToHome();
    }

    private void goToHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private void goToJoinOrCreateTeam() {
        Intent intent = new Intent(LoginActivity.this, JoinOrCreateTeamActivity.class);
        startActivity(intent);
    }

    private User getLoginUser(String usernameOrMail, String password, boolean stayLoggedIn) {
        PhpConnection connection = new PhpConnection(this);
        if (EMail.isValid(usernameOrMail)) {
            return connection.login(new EMail(usernameOrMail), password, stayLoggedIn);
        }
        else {
            return connection.login(usernameOrMail, password, stayLoggedIn);
        }
    }

    private void assignElements() {
        login = findViewById(R.id.login_loginbtn);
        register = findViewById(R.id.login_registerbtn);
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        stayLoggedInBox = findViewById(R.id.login_angemeldetbleiben);
    }
}