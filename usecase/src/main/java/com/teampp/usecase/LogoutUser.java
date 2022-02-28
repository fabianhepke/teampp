package com.teampp.usecase;

import com.teampp.domain.entities.User;
import com.teampp.domain.entities.valueobjects.Token;
import com.teampp.domain.repositories.UserRepository;

public class LogoutUser {
    private final UserRepository repository;

    public LogoutUser(UserRepository repository) {
        this.repository = repository;
    }

    public void logout(User user) {
        repository.changeUserLoginToken(user, new Token());
    }
}
