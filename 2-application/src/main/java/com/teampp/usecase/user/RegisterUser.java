package com.teampp.usecase.user;


import com.teampp.domain.user.ConcreteUserBuilder;
import com.teampp.domain.user.Rank;
import com.teampp.domain.user.User;
import com.teampp.domain.user.UserRepository;
import com.teampp.domain.user.Token;

public class RegisterUser {
    private final UserRepository repository;

    public RegisterUser(UserRepository repository) {
        this.repository = repository;
    }

    public void registerUser(String username, String name, String email, String password1) {
        User user = getUser(username, name, email, password1);

        repository.registerUser(user.getUsername().toString(),
                user.getPassword().toString(),
                user.geteMail().toString(),
                user.getLoginToken().toString(),
                user.getRank().rank,
                user.getName());
    }

    private User getUser(String username, String name, String email, String password1) {
        return new ConcreteUserBuilder()
                .setUsername(username)
                .setEmail(email)
                .setPassword(password1)
                .setName(name)
                .setLoginToken(new Token())
                .setRank(Rank.NORANK)
                .build();
    }

}
