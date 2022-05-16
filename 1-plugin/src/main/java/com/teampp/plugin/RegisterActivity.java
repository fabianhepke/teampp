package com.teampp.plugin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.team.R;
import com.teampp.plugin.database.UserRepositoryImpl;
import com.teampp.plugin.help.ErrorHelper;
import com.teampp.plugin.help.InputChecker;
import com.teampp.plugin.help.ChangeActivity;
import com.teampp.usecase.user.RegisterUser;


public class RegisterActivity extends AppCompatActivity{

    private Button register, login;
    private EditText username, email, password, password2, name;
    private UserRepositoryImpl userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        assignElements();
    }

    private void assignElements() {
        register = findViewById(R.id.register_registerbtn);
        login = findViewById(R.id.register_loginbtn);
        username = findViewById(R.id.register_username);
        name = findViewById(R.id.register_name);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password1);
        password2 = findViewById(R.id.register_password2);
        userRepository = new UserRepositoryImpl();
    }

    @Override
    protected void onResume() {
        super.onResume();
        assignButtonEvents();
    }

    private void assignButtonEvents() {
        register.setOnClickListener(v -> register());

        login.setOnClickListener(v -> goToLogin());
    }

    private void register() {
        if (InputChecker.isRegisterDataVaild(username.getText().toString(), email.getText().toString(), password.getText().toString(), password2.getText().toString())) {
            RegisterUser registerUseCase = new RegisterUser(userRepository);
            registerUseCase.registerUser(username.getText().toString(), name.getText().toString(), email.getText().toString(), password.getText().toString());
            ChangeActivity.changeActivity(RegisterActivity.this, LoginActivity.class);
        }
        else {
            ErrorHelper.setRegisterError(username, email, password, password2, name);
        }
    }

    private void goToLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}