package com.teampp.domain;

import com.teampp.domain.teamdate.Adress;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdressTest {

    @Test
    public void adressTest() {
        Adress adress = new Adress(76136, "Karlsruhe", "Erzbergerstraße", "120");
        Adress adress1 = new Adress(76316, "Malsch", "Bahnhofstraße", "1a");
        assertTrue(adress.equals(new Adress(76136, "Karlsruhe", "Erzbergerstraße", "120")));
        assertFalse(adress1.equals(adress));
        Exception exception = assertThrows(RuntimeException.class, () -> new Adress(-12123, "Testcity", "Testsraße", "1a"));
        assertTrue(exception.getMessage().contains("Falsches plz Format"));
    }
}
