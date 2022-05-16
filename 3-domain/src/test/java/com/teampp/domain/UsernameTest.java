package com.teampp.domain;

import com.teampp.domain.user.Username;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsernameTest {

    @Test
    public void usernameTest() {
        Username user = new Username("der-tester");
        Username user2 = new Username("der.andere");
        assertTrue(user.equals(new Username("der-tester")));
        assertFalse(user.equals(user2));
        Exception exception = assertThrows(RuntimeException.class, () -> new Username("kein username"));
        assertTrue(exception.getMessage().contains("Verbotenes Zeichen: nur Kleinbuchstaben(außer äöü), Punkt, Binde- und Untestrich sind zulässig!"));
        exception = assertThrows(RuntimeException.class, () -> new Username("123"));
        assertTrue(exception.getMessage().contains("Benutzername ist zu kurz"));
    }

}
