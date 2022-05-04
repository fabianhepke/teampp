package com.teampp.usecase.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.EditText;

import com.teampp.domain.user.ConcreteUserBuilder;
import com.teampp.domain.user.User;
import com.teampp.domain.general.exceptions.WrongInputSyntaxException;
import com.teampp.domain.user.UserRepository;
import com.teampp.usecase.ChangeActivity;
import com.teampp.usecase.help.ExistanceChecker;

public class LoginUser {
    private final UserRepository repository;
    private final Context context;
    private User user;
    private Class homeActivity, joinOrCreateActivity;

    public LoginUser(UserRepository repository, Context context, Class homeActivity, Class joinOrCreateTeam) {
        this.repository = repository;
        this.context = context;
        this.homeActivity = homeActivity;
        this.joinOrCreateActivity = joinOrCreateTeam;
    }

    public void loginUser(boolean stayLoggedIn, EditText username, EditText password) {
        user = getUser(username, password);

        if (!isDataValid(user, username, password)) {
            return;
        }
        User loggedInUser = getLoggedInUser(user, stayLoggedIn);
        if (loggedInUser == null) {
            return;
        }
        saveUserIDLocal(loggedInUser);
        saveLoginTokenLocal(loggedInUser);
        goToHomeOrJoinTeam();
    }

    private void goToHome() {
        ChangeActivity.changeActivity(context, homeActivity);
    }

    private void goToJoinOrCreateTeam() {
        ChangeActivity.changeActivity(context, joinOrCreateActivity);
    }

    private void goToHomeOrJoinTeam() {
        if (repository.doesUserHasTeam(user.getUsername().toString())) {
            goToJoinOrCreateTeam();
            return;
        }
        goToHome();
    }


    private User getLoggedInUser(User user, boolean stayLoggedIn) {
        if (user.geteMail() != null){
            return repository.loginWithEMail(user.geteMail().toString(), user.getPassword().toString(), stayLoggedIn);
        }
        return repository.loginWithUserName(user.getUsername().toString(), user.getPassword().toString(), stayLoggedIn);
    }

    private User getUser(EditText username, EditText password) {
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
        return user;
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
        if (user.geteMail() != null) {
            return  repository.doesPasswordMatchUserEMail(user.geteMail().toString(), user.getPassword().toString());
        }
        return repository.doesPasswordMatchUserName(user.getUsername().toString(), user.getPassword().toString());
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
