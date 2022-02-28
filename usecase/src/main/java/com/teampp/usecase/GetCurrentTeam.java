package com.teampp.usecase;

import com.teampp.domain.entities.Team;
import com.teampp.domain.entities.User;
import com.teampp.domain.entities.valueobjects.BasicID;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.repositories.UserRepository;

public class GetCurrentTeam {
    private TeamRepository teamRepository;
    private UserRepository userRepository;

    public GetCurrentTeam(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public Team getCurrentTeam(int userID) {
        Team team = new Team(userRepository.getCurrentTeam(new User(new BasicID(userID))).getTeamID());
        team.setMembers(teamRepository.getTeamMemberNum(team));
        team.setTeamName(teamRepository.getTeamName(team.getTeamID()));
        return team;
    }
}
