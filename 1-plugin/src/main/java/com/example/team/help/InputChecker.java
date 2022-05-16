package com.example.team.help;

import android.widget.EditText;

import com.example.team.database.TeamRepositoryImpl;
import com.example.team.database.UserRepositoryImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputChecker {

    public static boolean isLoginDataValid(String usernameOrMail, String password) {
        if (!isUsernameCorrect(usernameOrMail) && isMailCorrect(usernameOrMail)) {
            return false;
        }
        if (!isPasswordCorrect(password)) {
            return false;
        }
        return doesEmailExists(usernameOrMail) || doesUsernameExists(usernameOrMail);
    }

    public static boolean isTeamDateCorrect(EditText title, EditText plz, EditText place, EditText street, EditText hnr) {
        return isTitleCorrect(title.getText().toString())
                && isPlaceCorrect(place.getText().toString())
                && isStreetCorrect(street.getText().toString())
                && isPLZCorrect(plz.getText().toString())
                && isHnrCorrect(hnr.getText().toString());
    }

    public static boolean isTitleCorrect(String title) {
        return !title.equals("");
    }

    public static boolean isRegisterDataVaild(String username, String email, String password1, String password2) {
        return isUsernameCorrect(username)
                && isMailCorrect(email)
                && isPasswordCorrect(password1)
                && doesPasswordsMatch(password1, password2);
    }

    private static boolean doesPasswordsMatch(String password1, String password2) {
        return password1.equals(password2);
    }

    public static boolean doesUsernameExists(String username) {
        UserRepositoryImpl repository = new UserRepositoryImpl();
        return repository.doesUserNameExist(username);
    }

    public static boolean doesEmailExists(String email) {
        UserRepositoryImpl repository = new UserRepositoryImpl();
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

    public static boolean isPLZCorrect(String plz) {
        if (plz.equals("")) {
            return false;
        }
        return plz.length() == 5;
    }

    public static boolean isPlaceCorrect(String place) {
        return !place.equals("");
    }

    public static boolean isStreetCorrect(String street) {
        return !street.equals("");
    }

    public static boolean isHnrCorrect(String hnr) {
        return !hnr.equals("");
    }

    public static boolean doesPasswordMatchUser(String usernameOrMail, String password) {
        UserRepositoryImpl repository = new UserRepositoryImpl();
        if (isMailCorrect(usernameOrMail)) {
            return repository.doesPasswordMatchUserEMail(usernameOrMail, password);
        }
        return repository.doesPasswordMatchUserName(usernameOrMail, password);
    }

    public static boolean isCreateTeamCorrect(EditText teamnameInput, EditText descriptionInput, EditText teamPin) {
        return isTeamNameCorrect(teamnameInput) && isDescriptionCorrect(descriptionInput) && isPinCorrect(teamPin);
    }

    public static boolean isPinCorrect(EditText teamPin) {
        return teamPin.getText().toString().length() == 4;
    }

    public static boolean isDescriptionCorrect(EditText descriptionInput) {
        return !descriptionInput.getText().toString().equals("");
    }

    public static boolean isTeamNameCorrect(EditText teamnameInput) {
        return !teamnameInput.getText().toString().equals("");
    }

    public static boolean isJoinTeamCorrect(int userID, int teamID, int pin) {

        return !isUserInTeam(userID, teamID) && doesPinMatchTeam(teamID, pin);
    }

    public static boolean doesPinMatchTeam(int teamID, int pin) {
        TeamRepositoryImpl teamRepository = new TeamRepositoryImpl();
        return teamRepository.doesPinMatchTeam(teamID, pin);
    }

    public static boolean isUserInTeam(int userID, int teamID) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        return userRepository.doesUserHasTeamConnection(userID, teamID);
    }
}