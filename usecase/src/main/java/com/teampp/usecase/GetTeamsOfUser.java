package com.teampp.usecase;

import com.teampp.domain.entities.Team;
import com.teampp.domain.entities.valueobjects.Teams;
import com.teampp.domain.entities.User;
import com.teampp.domain.repositories.TeamRepository;

public class GetTeamsOfUser {
    private final TeamRepository repository;

    public GetTeamsOfUser(TeamRepository repository) {
        this.repository = repository;
    }

    public Teams getTeams(User user) {
        if (user.getUserID() == null) {
            return null;
        }
        Teams teams = repository.getTeamsOfUser(user);
        for (int i = 0; i<teams.getTeams().size(); i++) {
            Team tmp = teams.getTeams().get(i);
            tmp.setMembers(repository.getTeamMemberNum(tmp));
            teams.getTeams().set(i, tmp);
        }
        return teams;
    }
}
