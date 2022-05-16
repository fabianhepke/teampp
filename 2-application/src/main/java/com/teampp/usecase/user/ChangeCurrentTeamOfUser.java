package com.teampp.usecase.user;

import com.teampp.domain.user.ConcreteUserBuilder;
import com.teampp.domain.user.User;
import com.teampp.domain.user.UserRepository;

public class ChangeCurrentTeamOfUser {
    private final UserRepository repository;

    public ChangeCurrentTeamOfUser(UserRepository repository) {
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
