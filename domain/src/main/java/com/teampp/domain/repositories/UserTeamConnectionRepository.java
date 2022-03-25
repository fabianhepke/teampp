package com.teampp.domain.repositories;

import com.teampp.domain.entities.UserTeamConnection;

public interface UserTeamConnectionRepository {
    void addUserTeamConnection(int teamID, int userID, String rank);

    void removeUserTeamConnection(int teamID, int userID);

    void updateRank(int teamID, int userID, String rank);
}
