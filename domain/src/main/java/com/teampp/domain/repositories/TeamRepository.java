package com.teampp.domain.repositories;

import com.teampp.domain.valueobjects.*;
import com.teampp.domain.entities.*;

import org.json.JSONArray;

public interface TeamRepository {
    int getNewTeamID();

    void registerTeam(int teamID, String teamname, String description, int pin);

    boolean doesPinMatchTeam(int teamID, int pin);

    int getTeamMemberNum(int teamID);

    String getTeamName(int teamID);

    String getTeamsOfUser(int userID);

    Team getTeamByID(int teamID);
}
