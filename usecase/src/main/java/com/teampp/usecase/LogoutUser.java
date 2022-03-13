package com.teampp.usecase;

import com.teampp.domain.builder.ConcreteUserBuilder;
import com.teampp.domain.entities.User;
import com.teampp.domain.valueobjects.Token;
import com.teampp.domain.repositories.UserRepository;

public class LogoutUser {
    private final UserRepository repository;

    public LogoutUser(UserRepository repository) {
        this.repository = repository;
    }

    public void logout(int userID) {
        User user = new ConcreteUserBuilder()
                .setUserID(userID)
                .build();
        repository.changeUserLoginToken(user, new Token());
    }
}
