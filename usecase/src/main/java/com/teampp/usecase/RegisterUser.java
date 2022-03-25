package com.teampp.usecase;

import android.widget.EditText;

import com.teampp.domain.builder.ConcreteUserBuilder;
import com.teampp.domain.entities.User;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.domain.valueobjects.Token;
import com.teampp.usecase.help.ExistanceChecker;

public class RegisterUser {
    private final UserRepository repository;

    public RegisterUser(UserRepository repository) {
        this.repository = repository;
    }

    public boolean registerUser(EditText username, EditText name, EditText email, EditText password1, EditText password2) {
        User user = getUser(username, name, email, password1);
        if (!isInputValid(user, username, email, password1, password2)){
            return false;
        }
        return repository.registerUser(user.getUsername().toString(),
                user.getPassword().toString(),
                user.geteMail().toString(),
                user.getLoginToken().toString(),
                user.getRank().rank,
                user.getName());
    }

    private User getUser(EditText username, EditText name, EditText email, EditText password1) {
        return new ConcreteUserBuilder()
                .setUsername(username.getText().toString())
                .setEmail(email.getText().toString())
                .setPassword(password1.getText().toString())
                .setName(name.getText().toString())
                .setLoginToken(new Token())
                .build();
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
