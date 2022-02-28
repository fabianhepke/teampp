package com.teampp.domain.repositories;

import com.teampp.domain.entities.valueobjects.*;
import com.teampp.domain.entities.*;

public interface TeamRepository {
    int getNewTeamID();

    void registerTeam(Team team);

    boolean doesPinMatchTeam(Team team);

    int getTeamMemberNum(Team team);

    String getTeamName(TeamID teamID);

    Teams getTeamsOfUser(User user);
}
