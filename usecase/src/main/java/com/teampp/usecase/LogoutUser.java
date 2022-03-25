package com.teampp.usecase;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.teampp.domain.builder.ConcreteUserBuilder;
import com.teampp.domain.entities.User;
import com.teampp.domain.valueobjects.Token;
import com.teampp.domain.repositories.UserRepository;

public class LogoutUser {
    private final UserRepository repository;
    private final Context context;

    public LogoutUser(UserRepository repository, Context context) {
        this.repository = repository;
        this.context = context;
    }

    public void logout(int userID) {
        deleteLoginToken();
    }

    private void deleteLoginToken() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("login_token", null);
        editor.apply();
    }
}
