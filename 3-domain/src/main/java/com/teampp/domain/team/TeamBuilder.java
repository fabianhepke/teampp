package com.teampp.domain.team;

public interface TeamBuilder {
    TeamBuilder setTeamID(TeamID teamID);

    TeamBuilder setTeamname(String teamname);

    TeamBuilder setDescription(String description);

    TeamBuilder setPin(int pin);

    TeamBuilder setMembers(TeamRepository teamRepository, int teamID);

    Team build();
}
