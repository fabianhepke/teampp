package com.example.team.help;

import android.widget.LinearLayout;

import com.example.team.database.UserRepositoryImpl;

public class InsertUsers {
    private final int dateID;
    private final UserRepositoryImpl userRepository;

    public InsertUsers(int dateID, UserRepositoryImpl userRepository) {
        this.dateID = dateID;
        this.userRepository = userRepository;
    }

    public void insertUsers(LinearLayout container) {

    }

}
