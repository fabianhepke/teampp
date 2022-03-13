package com.teampp.domain.repositories;

import com.teampp.domain.valueobjects.*;
import com.teampp.domain.entities.*;

import org.json.JSONArray;

public interface TeamRepository {
    int getNewTeamID();

    void registerTeam(Team team);

    boolean doesPinMatchTeam(Team team);

    int getTeamMemberNum(Team team);

    String getTeamName(TeamID teamID);

    JSONArray getTeamsOfUser(User user);

    Team getTeamByID(int teamID);
}
