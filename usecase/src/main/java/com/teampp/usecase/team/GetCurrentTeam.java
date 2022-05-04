package com.teampp.usecase.team;

import com.teampp.domain.team.ConcreteTeamBuilder;
import com.teampp.domain.user.ConcreteUserBuilder;
import com.teampp.domain.team.Team;
import com.teampp.domain.user.User;
import com.teampp.domain.team.TeamRepository;
import com.teampp.domain.user.UserRepository;

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
