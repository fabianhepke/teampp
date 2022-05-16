package com.teampp.domain;

import com.teampp.domain.team.TeamID;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TeamIDTest {

    @Test
    public void teamIDTest() {
        TeamID teamID = new TeamID(123456);
        TeamID teamID2 = new TeamID(123457);
        assertTrue(teamID.equals(new TeamID(123456)));
        assertFalse(teamID.equals(teamID2));
        Exception exception = assertThrows(RuntimeException.class, () -> new TeamID(1234322));
        assertTrue(exception.getMessage().contains("TeamID muss genau 6 Stellen haben!"));
    }
}
