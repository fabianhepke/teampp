package com.teampp.usecase;

import com.teampp.domain.entities.User;
import com.teampp.domain.repositories.UserRepository;

public class ChangeCurrentTeam {
    private final UserRepository repository;

    public ChangeCurrentTeam(UserRepository repository) {
        this.repository = repository;
    }

    public void changeTeam(User user) {
        if (user.getUserID() == null) {
            return;
        }
        if (user.getTeamID() == null) {
            return;
        }
        repository.changeCurrentTeam(user);
    }
}
