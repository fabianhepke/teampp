package com.teampp.domain;

import com.teampp.domain.general.valueobjects.BasicID;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasicIDTest {

    @Test
    public void basicIDTest() {
        BasicID userID = new BasicID(126);
        BasicID userID2 = new BasicID(157);
        assertTrue(userID.equals(new BasicID(126)));
        assertFalse(userID.equals(userID2));
        Exception exception = assertThrows(RuntimeException.class, () -> new BasicID(-122));
        assertTrue(exception.getMessage().contains("User ID muss positiv sein"));
    }
}
