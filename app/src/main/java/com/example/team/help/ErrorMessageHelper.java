package com.example.team.help;

import android.widget.EditText;

public class ErrorMessageHelper {

    public static void displayRegisterError(EditText username, EditText email, EditText password, EditText password2) {
        if (!InputChecker.isUsernameValid(username.getText().toString())) {
            username.setError("Der Benutzername muss mindestens 5 Zeichen enthalten!");
        }
        if (!EMail.isValid(email.getText().toString())) {
            email.setError("Keine gültige E-Mail Adresse!");
        }
        if (!InputChecker.isPasswordValid(password.getText().toString())) {
            password.setError("Das Passwort muss mindestens 8 Zeichen enthalten!");
        }
        if (!InputChecker.password2EqualsPassword(password.getText().toString(), password2.getText().toString())) {
            password2.setError("Stimmt nicht mit dem Passwort überein!");
        }
    }


}
