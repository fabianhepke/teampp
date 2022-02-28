package com.example.team.help;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EMailHelper {
    private final String mail;
    private static final Pattern MAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public EMailHelper(String mail) {
        if (!isValid(mail)) {
            throw new IllegalArgumentException();
        }
        this.mail = mail;
    }

    public String toString() {
        return mail;
    }

    public static boolean isValid(String mail) {
        Matcher matcher = MAIL_REGEX.matcher(mail);
        return matcher.find();
    }

}
