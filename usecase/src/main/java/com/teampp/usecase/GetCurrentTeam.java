package com.teampp.usecase;

import com.teampp.domain.builder.ConcreteTeamBuilder;
import com.teampp.domain.builder.ConcreteUserBuilder;
import com.teampp.domain.entities.Team;
import com.teampp.domain.entities.User;
import com.teampp.domain.valueobjects.BasicID;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.domain.valueobjects.TeamID;

public class GetCurrentTeam {
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final Team team;

    public GetCurrentTeam(TeamRepository teamRepository, UserRepository userRepository, int userID) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        User user = new ConcreteUserBuilder().setUserID(userID).build();
        int teamID = userRepository.getCurrentTeam(user.getUserID().toInt()).getTeamID().toInt();
        team = new ConcreteTeamBuilder().setTeamID(teamID)
                .setMembers(teamRepository, teamID)
                .setTeamname(teamRepository.getTeamName(teamID))
                .build();
    }

    public Team getTeam() {
        return team;
    }

    public int getCurrentTeamID() {
        return team.getTeamID().toInt();
    }
    public String getCurrentTeamName() {
        return team.getTeamName();
    }
    public int getCurrentTeamMemberNum() {
        return team.getMembers();
    }
}
