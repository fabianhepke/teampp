package com.teampp.usecase;

import android.widget.EditText;

import com.teampp.domain.entities.User;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.usecase.help.ExistanceChecker;

public class ChangeProfileData {

    private final UserRepository repository;

    public ChangeProfileData(UserRepository repository) {
        this.repository = repository;
    }

    public void changeProfileData(User oldUser, User newUser, EditText username, EditText email, EditText password1, EditText password2) {
        if(!isInputValid(oldUser, newUser, username, email, password1, password2)) {
            repository.changeUserData(newUser);
        }
    }

    private boolean isInputValid(User oldUser, User newUser, EditText username, EditText email, EditText password1, EditText password2) {
        if (!newUser.getUsername().equals(oldUser.getUsername()) && ExistanceChecker.doesUsernameExists(newUser, repository)) {
            username.setError("Benutzername ist vergeben!");
            return false;
        }
        if (!newUser.geteMail().equals(oldUser.geteMail()) && ExistanceChecker.doesEmailExists(newUser, repository)) {
            email.setError("Email Adresse ist vergeben!");
            return false;
        }
        if (!password1.getText().toString().equals(password2.getText().toString())) {
            password2.setError("Passwörter stimmen nicht überein");
        }
        return true;
    }
}
