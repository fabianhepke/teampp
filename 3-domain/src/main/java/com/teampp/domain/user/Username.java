package com.teampp.domain.user;

import com.teampp.domain.general.exceptions.WrongInputSyntaxException;

import java.util.Objects;

public final class Username {
    private final String username;

    public Username(String username){
        if (username.length() < 4) {
            throw new WrongInputSyntaxException("Benutzername ist zu kurz");
        }

        for (int i = 0; i < username.length(); i++) {
            String legalChars = "qwertzuiopasdfghjklyxcvbnm_1234567890.-";
            if (!legalChars.contains(String.valueOf(username.charAt(i)))) {
                throw new WrongInputSyntaxException("Verbotenes Zeichen: nur Kleinbuchstaben(außer äöü), Punkt, Binde- und Untestrich sind zulässig!");
            }
        }

        this.username = username;
    }

    @Override
    public String toString() {
        return this.username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username username1 = (Username) o;
        return Objects.equals(username, username1.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
