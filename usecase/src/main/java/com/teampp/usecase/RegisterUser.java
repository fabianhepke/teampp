package com.teampp.usecase;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.EditText;

import com.teampp.domain.entities.User;
import com.teampp.domain.entities.valueobjects.EMail;
import com.teampp.domain.entities.valueobjects.Password;
import com.teampp.domain.entities.valueobjects.Username;
import com.teampp.domain.factories.UserFactory;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.usecase.help.ExistanceChecker;

public class RegisterUser {
    private final UserRepository repository;

    public RegisterUser(UserRepository repository) {
        this.repository = repository;
    }

    public boolean registerUser(EditText username, EditText email, EditText password1, EditText password2) {
        User user = getUser(username, email, password1);
        if (!isInputValid(user, username, email, password1, password2)){
            return false;
        }
        return repository.registerUser(user);
    }

    private User getUser(EditText username, EditText email, EditText password1) {
        UserFactory userFactory = new UserFactory();
        return userFactory.getUser(username.getText().toString(), email.getText().toString(), password1.getText().toString());
    }

    private boolean isInputValid(User user, EditText username, EditText email, EditText password1, EditText password2) {
        if (ExistanceChecker.doesUsernameExists(user, repository)) {
            username.setError("Benutzername ist vergeben");
            return false;
        }
        if (ExistanceChecker.doesEmailExists(user, repository)) {
            email.setError("Email Adresse ist bereits registriert");
            return false;
        }
        if (!password1.getText().toString().equals(password2.getText().toString())) {
            password2.setError("Passwort stimmt nicht überein");
            return false;
        }
        return true;
    }
}
