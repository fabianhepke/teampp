package com.example.team.help;

import android.widget.EditText;
import android.widget.TextView;

import com.example.team.help.InputChecker;

public class ErrorHelper {

    public static void setRegisterError(EditText usernameView, EditText emailView, EditText passwordView1, EditText passwordView2, EditText nameView) {
        String username = usernameView.getText().toString();
        String email = emailView.getText().toString();
        String password1 = passwordView1.getText().toString();
        String password2 = passwordView2.getText().toString();
        String name = nameView.getText().toString();

        checkRegisterUsername(usernameView, username);
        checkRegisterEmail(emailView, email);
        checkRegisterPassword(passwordView1, password1);
        checkSecondPassword(passwordView2, password1, password2);
        checkName(nameView, name);
    }

    private static void checkName(EditText nameView, String name) {
        if (name.length() < 2) {
            nameView.setError("Der Name muss mindestens 2 Buchstaben haben");
        }
    }

    private static void checkSecondPassword(EditText passwordView2, String password1, String password2) {
        if (!password1.equals(password2)) {
            passwordView2.setError("Die Passwörter stimmen nicht miteinander überein!");
        }
    }

    private static void checkRegisterPassword(EditText passwordView1, String password1) {
        if (!InputChecker.isPasswordCorrect(password1)) {
            passwordView1.setError("Das Password muss mindestens 4 Zeichen haben");
        }
    }

    private static void checkRegisterEmail(EditText emailView, String email) {
        if (InputChecker.doesEmailExists(email)) {
            emailView.setError("Diese E-Mail Adresse wird bereits für einen anderen Account verwendet!");
        }
        if (!InputChecker.isMailCorrect(email)) {
            emailView.setError("Keine korrekte Email!");
        }
    }

    private static void checkRegisterUsername(EditText usernameView, String username) {
        if (InputChecker.doesUsernameExists(username)) {
            usernameView.setError("Benutzername ist bereits vergeben!");
        }
        if (!InputChecker.isUsernameCorrect(username)) {
            usernameView.setError("Verbotenes Zeichen: nur Kleinbuchstaben(außer äöü), Punkt, Binde- und Untestrich sind zulässig!");
        }
    }

    public static void setLoginError(EditText usernameView, EditText passwordView) {
        String password = passwordView.getText().toString();
        String usernameOrMail = usernameView.getText().toString();

        checkLoginPassword(passwordView);

        checkLoginUserOrMail(usernameView, usernameOrMail);

        checkPasswordMatchesUsername(passwordView, password, usernameOrMail);

    }

    private static void checkPasswordMatchesUsername(EditText passwordView, String password, String usernameOrMail) {
        if (!InputChecker.doesPasswordMatchUser(usernameOrMail, password)) {
            passwordView.setError("Passwort und Benutzername stimmen nicht überein!");
        }
    }

    private static void checkLoginUserOrMail(EditText usernameView, String usernameOrMail) {
        if (InputChecker.isMailCorrect(usernameOrMail)) {
            if (!InputChecker.doesEmailExists(usernameOrMail)) {
                usernameView.setError("Email-Adresse ist nicht registriert!");
            }
        }
        else {
            if (!InputChecker.doesUsernameExists(usernameOrMail)) {
                usernameView.setError("Benutzername existiert nicht");
            }
        }
    }

    private static void checkLoginPassword(EditText passwordView) {
        if (!InputChecker.isPasswordCorrect(passwordView.getText().toString())) {
            passwordView.setError("Das Passwort muss mind. 4 Zeichen enthalten und darf folgende Buchstaben nicht beinhalten: öüäÖÜÄ");
        }
    }

    public static void setCreateTeamError(EditText teamnameInput, EditText descriptionInput, EditText teamPin) {
        if (!InputChecker.isTeamNameCorrect(teamnameInput)) {
            teamnameInput.setError("Teamname eingeben!");
        }
        if (!InputChecker.isDescriptionCorrect(descriptionInput)) {
            descriptionInput.setError("Beschreibung eingeben!");
        }
        if (!InputChecker.isPinCorrect(teamPin)) {
            teamPin.setError("Vierstelligen PIN eingeben!");
        }
    }

    public static void setTeamDateError(EditText title, EditText plz, EditText place, EditText street, EditText hnr) {
        if (!InputChecker.isPLZCorrect(plz.getText().toString())) {
            plz.setError("Postleitzahl muss 5 Zahlen enthalten!");
        }
        if (!InputChecker.isStreetCorrect(street.getText().toString())) {
            street.setError("Keine Straße angegeben!");
        }
        if (!InputChecker.isPlaceCorrect(place.getText().toString())) {
            place.setError("Kein Ort angegeben!");
        }
        if (!InputChecker.isHnrCorrect(hnr.getText().toString())) {
            hnr.setError("Keine Hausnummer angegeben!");
        }
        if (!InputChecker.isTitleCorrect(title.getText().toString())) {
            title.setError("Kein Titel angegeben!");
        }
    }

    public static void setJoinTeamError(int userID, int teamID, int teamPin, TextView pinError) {
        if (!InputChecker.doesPinMatchTeam(teamID, teamPin)) {
            pinError.setText("TeamID und Pin stimmen nicht miteinander überein!");
        }
        if (InputChecker.isUserInTeam(userID, teamID)) {
            pinError.setText("Sie sind bereits in dem Team!");
        }
    }
}
