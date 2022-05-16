package com.teampp.domain;

import com.teampp.domain.user.EMail;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EMailTest {

    @Test
    public void eMailTest() {
        EMail mail = new EMail("test@example.com");
        EMail mail2 = new EMail("asdfk@asldfkj.de");
        assertTrue(mail.equals(new EMail("test@example.com")));
        assertFalse(mail.equals(mail2));
        Exception exception = assertThrows(RuntimeException.class, () -> new EMail("dasistkeine@mail."));
        assertTrue(exception.getMessage().contains("Falsches Format"));
    }
}
