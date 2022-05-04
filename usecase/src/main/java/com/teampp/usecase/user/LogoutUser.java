package com.teampp.usecase.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.teampp.domain.user.UserRepository;

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
