package com.teampp.domain;

import com.teampp.domain.user.Password;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PasswordTest {

    @Test
    public void passwordTest() {
        Password password = new Password("12345");
        Password password2 = new Password("23452");
        assertTrue(password.equals(new Password("12345")));
        assertFalse(password.equals(password2));
        Exception exception = assertThrows(RuntimeException.class, () -> new Password("123"));
        assertTrue(exception.getMessage().contains("Passwort ist zu kurz"));
        exception = assertThrows(RuntimeException.class, () -> new Password("12äasf3"));
        assertTrue(exception.getMessage().contains("Verbotenes Zeichen: öäüÖÄÜ"));
    }
}
