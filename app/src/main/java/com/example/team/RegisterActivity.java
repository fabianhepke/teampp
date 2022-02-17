package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.team.components.User;
import com.example.team.database.PhpConnection;
import com.example.team.help.EMail;
import com.example.team.help.ErrorMessageHelper;
import com.example.team.help.InputChecker;
import com.example.team.help.Token;


public class RegisterActivity extends AppCompatActivity{

    private Button register, login;
    private EditText username, email, password, password2;
    private User user;

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
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password1);
        password2 = findViewById(R.id.register_password2);
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
                if (!InputChecker.isRegisterDataValid(username.getText().toString(), email.getText().toString(), password.getText().toString(), password2.getText().toString())
                        && InputChecker.doesUserExists(username.getText().toString())
                        && InputChecker.doesUserExists( email.getText().toString())) {

                    ErrorMessageHelper.displayRegisterError(username, email, password, password2);
                }
                else {
                    String result = register();
                    if(!result.equals("successfull")){
                        return;
                    }
                    goToLogin();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });
    }

    private String register() {
        user = createUserFromRegisterForm();
        PhpConnection connection = new PhpConnection();
        return connection.registerUser(user);
    }



    private User createUserFromRegisterForm() {
        String newUsername = username.getText().toString();
        EMail newEmail = new EMail(email.getText().toString());
        String newPassword = password.getText().toString();
        User user = new User(newUsername, newEmail.toString(), newPassword);
        user.setLoginToken(new Token());
        user.setVerifyToken(new Token());
        return user;
    }

    private void goToLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}