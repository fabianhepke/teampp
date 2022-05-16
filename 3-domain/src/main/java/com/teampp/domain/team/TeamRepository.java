package com.teampp.domain.team;

public interface TeamRepository {
    int getNewTeamID();

    void registerTeam(int teamID, String teamname, String description, int pin);

    boolean doesPinMatchTeam(int teamID, int pin);

    int getTeamMemberNum(int teamID);

    String getTeamName(int teamID);

    String getTeamsOfUser(int userID);

    Team getTeamByID(int teamID);
}
