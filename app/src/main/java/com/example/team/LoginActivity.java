package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.team.database.UserRepositoryImpl;
import com.example.team.help.ActivityChanger;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.usecase.LoginUser;


public class LoginActivity extends AppCompatActivity {

    private EditText usernameView, passwordView;
    private Button login, register;
    private CheckBox stayLoggedInBox;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        assignElements();
        userRepository = new UserRepositoryImpl();
    }


    @Override
    protected void onResume() {
        super.onResume();
        setButtonClickEvents();
    }

    private void setButtonClickEvents() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityChanger.changeActivityTo(LoginActivity.this, RegisterActivity.class);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        LoginUser loginUseCase = new LoginUser(userRepository, LoginActivity.this);
        boolean isLoginsuccessful = loginUseCase.loginUser(stayLoggedInBox.isChecked(), usernameView, passwordView);
        if (isLoginsuccessful) {
            goToHomeOrJoinTeam();
        }
    }

    private void goToHomeOrJoinTeam() {
        if (userRepository.doesUserHasTeam(usernameView.getText().toString())) {
            goToJoinOrCreateTeam();
            return;
        }
        goToHome();
    }

    private void goToHome() {
        ActivityChanger.changeActivityTo(LoginActivity.this, HomeActivity.class);
    }

    private void goToJoinOrCreateTeam() {
        ActivityChanger.changeActivityTo(LoginActivity.this, JoinOrCreateTeamActivity.class);
    }

    private void assignElements() {
        login = findViewById(R.id.login_loginbtn);
        register = findViewById(R.id.login_registerbtn);
        usernameView = findViewById(R.id.login_username);
        passwordView = findViewById(R.id.login_password);
        stayLoggedInBox = findViewById(R.id.login_angemeldetbleiben);
    }
}