package com.teampp.domain.repositories;

import com.teampp.domain.entities.UserTeamConnection;

public interface UserTeamConnectionRepository {
    void addUserTeamConnection(UserTeamConnection userTeamConnection);

    void removeUserTeamConnection(UserTeamConnection userTeamConnection);

    void updateRank(UserTeamConnection userTeamConnection);
}
