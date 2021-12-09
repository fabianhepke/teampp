package com.example.team.help;

import android.content.Context;

import com.example.team.components.User;
import com.example.team.database.PhpConnection;

public class InputChecker {

    public static boolean checkRegisterData(String username, String email, String password, String password2) {
        if (!isUsernameValid(username)) {
            return false;
        }
        if (!isPasswordValid(password)) {
            return false;
        }
        if (!password2EqualsPassword(password, password2)) {
            return false;
        }
        if (!EMail.isValid(email)) {
            return false;
        }
        return true;
    }

    public static boolean isUsernameValid(String username) {
        if (username.length() < 5) {
            return false;
        }
        return true;
    }

    public static boolean isPasswordValid (String password) {
        if (password.length() < 8) {
            return false;
        }
        return true;
    }

    public static boolean password2EqualsPassword (String password, String password2) {
        if (!password.equals(password2)) {
            return false;
        }
        return true;
    }

    public static boolean doesUserExists(Context context, String usernameOrEmail) {
        PhpConnection connection = new PhpConnection(context);
        if (EMail.isValid(usernameOrEmail)) {
            return connection.doesUserEmailExist(usernameOrEmail);
        }
        return connection.doesUserNameExist(usernameOrEmail);
    }

    public static boolean doesPassowrdMatchUser(Context context, String usernameOrEmail, String password) {
        PhpConnection connection = new PhpConnection(context);
        if (EMail.isValid(usernameOrEmail)) {
            return connection.doesPasswordMatchEmail(usernameOrEmail, password);
        }
        return connection.doesPasswordMatchUsername(usernameOrEmail, password);
    }

    public static boolean checkLoginData(Context context, String usernameOrEmail, String password) {
        if(!doesUserExists(context, usernameOrEmail)) {
            return false;
        }
        if (!doesPassowrdMatchUser(context, usernameOrEmail, password)) {
            return false;
        }
        return true;
    }
}
