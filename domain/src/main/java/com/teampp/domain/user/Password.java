package com.teampp.domain.user;

import com.teampp.domain.general.exceptions.WrongInputSyntaxException;

import java.util.Objects;

public final class Password {
    private final String password;

    public Password(String password) {
        if (password.length() < 4) {
            throw new WrongInputSyntaxException("Passwort ist zu kurz");
        }

        String illegalChars = "öäüÖÄÜ";
        for (int i = 0; i < illegalChars.length(); i++) {
            if (password.contains(String.valueOf(illegalChars.charAt(i)))) {
                throw new WrongInputSyntaxException("Verbotenes Zeichen: öäüÖÄÜ ");
            }
        }

        this.password = password;
    }

    @Override
    public String toString() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return Objects.equals(password, password1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }
}
