package com.teampp.domain.builder;

import com.teampp.domain.entities.Team;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.valueobjects.TeamID;

public class ConcreteTeamBuilder implements  TeamBuilder{
    private TeamID teamID;
    private String teamname, description;
    private int pin, members;

    @Override
    public ConcreteTeamBuilder setTeamID(TeamID teamID) {
        this.teamID = teamID;
        return this;
    }

    public ConcreteTeamBuilder setTeamID(int teamID) {
        this.teamID = new TeamID(teamID);
        return this;
    }

    @Override
    public ConcreteTeamBuilder setTeamname(String teamname) {
        this.teamname = teamname;
        return this;
    }

    @Override
    public ConcreteTeamBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public ConcreteTeamBuilder setPin(int pin) {
        this.pin = pin;
        return this;
    }

    @Override
    public ConcreteTeamBuilder setMembers(TeamRepository teamRepository, int teamID) {
        Team team = new Team();
        team.setTeamID(new TeamID(teamID));
        this.members=  teamRepository.getTeamMemberNum(team);
        return this;
    }

    public Team build() {
        Team team = new Team();
        team.setTeamID(this.teamID);
        team.setTeamName(this.teamname);
        team.setDescription(this.description);
        team.setMembers(this.members);
        team.setPin(this.pin);
        return team;
    }
}
