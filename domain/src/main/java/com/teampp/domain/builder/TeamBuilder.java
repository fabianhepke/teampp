package com.teampp.domain.builder;

import com.teampp.domain.entities.Team;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.valueobjects.TeamID;

public interface TeamBuilder {
    TeamBuilder setTeamID(TeamID teamID);

    TeamBuilder setTeamname(String teamname);

    TeamBuilder setDescription(String description);

    TeamBuilder setPin(int pin);

    TeamBuilder setMembers(TeamRepository teamRepository, int teamID);

    Team build();
}
