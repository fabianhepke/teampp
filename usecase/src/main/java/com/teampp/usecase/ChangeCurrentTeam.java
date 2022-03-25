package com.teampp.usecase;

import com.teampp.domain.builder.ConcreteUserBuilder;
import com.teampp.domain.entities.User;
import com.teampp.domain.repositories.UserRepository;

public class ChangeCurrentTeam {
    private final UserRepository repository;

    public ChangeCurrentTeam(UserRepository repository) {
        this.repository = repository;
    }

    public void changeTeam(int userID, int teamID) {
        User user = getUser(userID, teamID);
        repository.changeCurrentTeam(user.getUserID().toInt(), user.getActualTeamID());
    }

    private User getUser(int userID, int teamID) {
        return new ConcreteUserBuilder().setUserID(userID).setActualTeamID(teamID).build();
    }
}
