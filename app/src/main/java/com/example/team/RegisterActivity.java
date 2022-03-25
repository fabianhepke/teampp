package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.team.database.UserRepositoryImpl;
import com.teampp.usecase.ChangeActivity;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.usecase.RegisterUser;


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
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });
    }

    private void register() {
        RegisterUser registerUseCase = new RegisterUser(userRepository);
        boolean isSuccessfull = registerUseCase.registerUser(username, name, email, password, password2);
        if (!isSuccessfull) {
            return;
        }
        ChangeActivity.changeActivity(RegisterActivity.this, LoginActivity.class);
    }

    private void goToLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}