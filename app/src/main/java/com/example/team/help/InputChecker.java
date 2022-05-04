package com.example.team.help;

import com.example.team.database.UserRepositoryImpl;
import com.teampp.domain.general.exceptions.WrongInputSyntaxException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputChecker {

    public static boolean doesUsernameExists(String username, UserRepositoryImpl repository) {
        return repository.doesUserNameExist(username);
    }

    public static boolean doesEmailExists(String email, UserRepositoryImpl repository) {
        return repository.doesUserEmailExist(email);
    }

    public static boolean isUsernameCorrect(String username) {
        if (username.length() < 4) {
            return false;
        }
        for (int i = 0; i < username.length(); i++) {
            String legalChars = "qwertzuiopasdfghjklyxcvbnm_1234567890.-";
            if (!legalChars.contains(String.valueOf(username.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPasswordCorrect(String password) {
        if (password.length() < 4) {
            return false;
        }

        String illegalChars = "öäüÖÄÜ";
        for (int i = 0; i < illegalChars.length(); i++) {
            if (password.contains(String.valueOf(illegalChars.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMailCorrect(String email) {
        Pattern MAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = MAIL_REGEX.matcher(email);
        return matcher.find();
    }

    public static boolean isPLZCorrect(int plz) {
        return plz >= 0 && String.valueOf(plz).length() == 5;
    }

    public static boolean isPlaceCorrect(String place) {
        return place != null;
    }

    public static boolean isStreetCorrect(String street) {
        return street != null;
    }

    public static boolean isHnrCorrect(String hnr) {
        return hnr != null;
    }

}
