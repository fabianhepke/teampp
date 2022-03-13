package com.teampp.domain;

import com.teampp.domain.valueobjects.Adress;
import com.teampp.domain.valueobjects.EMail;
import com.teampp.domain.valueobjects.Password;
import com.teampp.domain.valueobjects.TeamID;
import com.teampp.domain.valueobjects.Token;
import com.teampp.domain.valueobjects.BasicID;
import com.teampp.domain.valueobjects.Username;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValueObjectTests {

    @Test
    public void EMailTest() {
        EMail mail = new EMail("test@example.com");
        EMail mail2 = new EMail("asdfk@asldfkj.de");
        assertTrue(mail.equals(new EMail("test@example.com")));
        assertFalse(mail.equals(mail2));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new EMail("dasistkeine@mail.");
        });
        assertTrue(exception.getMessage().contains("Falsches Format"));
    }

    @Test
    public void UsernameTest() {
        Username user = new Username("der-tester");
        Username user2 = new Username("der.andere");
        assertTrue(user.equals(new Username("der-tester")));
        assertFalse(user.equals(user2));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new Username("kein username");
        });
        assertTrue(exception.getMessage().contains("Verbotenes Zeichen: nur Kleinbuchstaben(außer äöü), Punkt, Binde- und Untestrich sind zulässig!"));
        exception = assertThrows(RuntimeException.class, () -> {
            new Username("123");
        });
        assertTrue(exception.getMessage().contains("Benutzername ist zu kurz"));
    }

    @Test
    public void PasswordTest() {
        Password password = new Password("12345");
        Password password2 = new Password("23452");
        assertTrue(password.equals(new Password("12345")));
        assertFalse(password.equals(password2));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new Password("123");
        });
        assertTrue(exception.getMessage().contains("Passwort ist zu kurz"));
        exception = assertThrows(RuntimeException.class, () -> {
            new Password("12äasf3");
        });
        assertTrue(exception.getMessage().contains("Verbotenes Zeichen: öäüÖÄÜ"));
    }

    @Test
    public void TokenTest() {
        Token token = new Token();
        Token token1 = new Token("123e4567-e89b-12d3-a456-556642440000");
        assertTrue(token1.equals(new Token("123e4567-e89b-12d3-a456-556642440000")));
        assertFalse(token.equals(token1));
    }

    @Test
    public void TeamIDTest() {
        TeamID teamID = new TeamID(123456);
        TeamID teamID2 = new TeamID(123457);
        assertTrue(teamID.equals(new TeamID(123456)));
        assertFalse(teamID.equals(teamID2));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new TeamID(1234322);
        });
        assertTrue(exception.getMessage().contains("TeamID muss genau 6 Stellen haben!"));
    }

    @Test
    public void UserIDTest() {
        BasicID userID = new BasicID(126);
        BasicID userID2 = new BasicID(157);
        assertTrue(userID.equals(new BasicID(126)));
        assertFalse(userID.equals(userID2));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new BasicID(-122);
        });
        assertTrue(exception.getMessage().contains("User ID muss positiv sein"));
    }

    @Test
    public void AdressTest() {
        Adress adress = new Adress(76136, "Karlsruhe", "Erzbergerstraße", "120");
        Adress adress1 = new Adress(76316, "Malsch", "Bahnhofstraße", "1a");
        assertTrue(adress.equals(new Adress(76136, "Karlsruhe", "Erzbergerstraße", "120")));
        assertFalse(adress1.equals(adress));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new Adress(-12123, "Testcity", "Testsraße", "1a");
        });
        assertTrue(exception.getMessage().contains("Falsches plz Format"));
    }
}
