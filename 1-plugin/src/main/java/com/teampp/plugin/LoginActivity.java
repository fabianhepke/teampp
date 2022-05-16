package com.teampp.plugin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.team.R;
import com.teampp.plugin.database.UserRepositoryImpl;
import com.teampp.plugin.help.ErrorHelper;
import com.teampp.plugin.help.InputChecker;
import com.teampp.plugin.help.ChangeActivity;
import com.teampp.usecase.user.LoginUser;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameView, passwordView;
    private Button login, register;
    private CheckBox stayLoggedInBox;
    private UserRepositoryImpl userRepository;

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
        register.setOnClickListener(v -> ChangeActivity.changeActivity(LoginActivity.this, RegisterActivity.class));
        login.setOnClickListener(v -> login());
    }

    private void login() {
        if (!InputChecker.isLoginDataValid(usernameView.getText().toString(), passwordView.getText().toString())) {
            ErrorHelper.setLoginError(usernameView, passwordView);
            return;
        }
        LoginUser loginUseCase = new LoginUser(userRepository);
        loginUseCase.loginUser(stayLoggedInBox.isChecked(), usernameView.getText().toString(), passwordView.getText().toString());
        if (!loginUseCase.wasLoginSuccessful()) {
            return;
        }
        saveUserIDLocal(loginUseCase.getUserID());
        if (loginUseCase.getToken() != null) {
            saveLoginTokenLocal(loginUseCase.getToken());
        }
        goToHomeOrJoinTeam();
    }

    private void goToHomeOrJoinTeam() {
        if (!userRepository.doesUserHasTeam(usernameView.getText().toString())) {
            goToJoinOrCreateTeam();
            return;
        }
        goToHome();
    }

    private void goToJoinOrCreateTeam() {
        ChangeActivity.changeActivity(this, JoinOrCreateTeamActivity.class);
    }

    private void goToHome() {
        ChangeActivity.changeActivity(this, HomeActivity.class);
    }

    private void saveUserIDLocal(int userID) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("user_id", userID);
        editor.apply();
    }

    private void saveLoginTokenLocal(String token) {
        if (token == null) {
            return;
        }
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("login_token", token);
        editor.apply();
    }

    private void assignElements() {
        login = findViewById(R.id.login_loginbtn);
        register = findViewById(R.id.login_registerbtn);
        usernameView = findViewById(R.id.login_username);
        passwordView = findViewById(R.id.login_password);
        stayLoggedInBox = findViewById(R.id.login_angemeldetbleiben);
    }
}