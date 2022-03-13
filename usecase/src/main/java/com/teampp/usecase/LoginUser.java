package com.teampp.usecase;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.EditText;

import com.teampp.domain.builder.ConcreteUserBuilder;
import com.teampp.domain.entities.User;
import com.teampp.domain.entities.exceptions.WrongInputSyntaxException;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.usecase.help.ExistanceChecker;

public class LoginUser {
    private final UserRepository repository;
    private final Context context;

    public LoginUser(UserRepository repository, Context context) {
        this.repository = repository;
        this.context = context;
    }

    public boolean loginUser(boolean stayLoggedIn, EditText username, EditText password) {
        User user;
        try {
            user = new ConcreteUserBuilder()
                    .setEmail(username.getText().toString())
                    .setPassword(password.getText().toString())
                    .build();
        } catch (WrongInputSyntaxException e) {
            user = new ConcreteUserBuilder()
                    .setUsername(username.getText().toString())
                    .setPassword(password.getText().toString())
                    .build();
        }

        if (!isDataValid(user, username, password)) {
            return false;
        }
        User loggedInUser = repository.login(user, stayLoggedIn);
        if (loggedInUser == null) {
            return false;
        }
        saveUserIDLocal(loggedInUser);
        saveLoginTokenLocal(loggedInUser);
        return true;
    }

    private boolean isDataValid(User user, EditText username, EditText password) {
        if (user.getUsername() != null) {
            if (!ExistanceChecker.doesUsernameExists(user, repository)) {
                username.setError("Benutzername existiert nicht");
                return false;
            }
        }
        if (user.geteMail() != null) {
            if (ExistanceChecker.doesEmailExists(user, repository)) {
                username.setError("Email Adresse existiert nicht");
                return false;
            }
        }
        if (!doesUserMatchPassword(user)) {
            password.setError("Falsches Passwort");
            return false;
        }
        return true;
    }

    private boolean doesUserMatchPassword(User user) {
        return repository.doesPasswordMatchUser(user);
    }


    private void saveLoginTokenLocal(User loggedInUser) {
        if (loggedInUser.getLoginToken() == null) {
            return;
        }
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("login_token", loggedInUser.getLoginToken().toString());
        editor.apply();
    }

    private void saveUserIDLocal(User loggedInUser) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("user_id", loggedInUser.getUserID().toInt());
        editor.apply();
    }
}
