package com.teampp.usecase;

import com.teampp.domain.entities.User;
import com.teampp.domain.factories.UserFactory;
import com.teampp.domain.repositories.UserRepository;

public class ChangeCurrentTeam {
    private final UserRepository repository;

    public ChangeCurrentTeam(UserRepository repository) {
        this.repository = repository;
    }

    public void changeTeam(int userID, int teamID) {
        User user = getUser(userID, teamID);
        repository.changeCurrentTeam(user);
    }

    private User getUser(int userID, int teamID) {
        UserFactory userFactory = new UserFactory();
        User user = userFactory.getUser(userID, repository);
        user.setActualTeamID(teamID);
        return user;
    }
}
