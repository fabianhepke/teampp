package com.teampp.domain.userteamconnection;

public interface UserTeamConnectionRepository {
    void addUserTeamConnection(int teamID, int userID, String rank);

    void removeUserTeamConnection(int teamID, int userID);

    void updateRank(int teamID, int userID, String rank);
}
