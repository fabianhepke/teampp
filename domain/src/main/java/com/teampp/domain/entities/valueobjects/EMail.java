package com.teampp.domain.entities.valueobjects;

import com.teampp.domain.entities.exceptions.WrongInputSyntaxException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EMail {
    private final String mail;
    private static final Pattern MAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public EMail(String mail) {
        if (!isValid(mail)) {
            throw new WrongInputSyntaxException("Falsches Format");
        }
        this.mail = mail;
    }

    @Override
    public String toString() {
        return mail;
    }

    public static boolean isValid(String mail) {
        Matcher matcher = MAIL_REGEX.matcher(mail);
        return matcher.find();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EMail eMail = (EMail) o;
        return Objects.equals(mail, eMail.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }
}
