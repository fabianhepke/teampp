package com.teampp.domain.builder;

import com.teampp.domain.entities.User;
import com.teampp.domain.entities.enums.Rank;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.domain.valueobjects.BasicID;
import com.teampp.domain.valueobjects.EMail;
import com.teampp.domain.valueobjects.Password;
import com.teampp.domain.valueobjects.Token;
import com.teampp.domain.valueobjects.Username;

import java.util.List;

public class ConcreteUserBuilder implements UserBuilder{
    private BasicID userID;
    private EMail eMail;
    private Password password;
    private Username username;
    private Token loginToken;
    private Rank rank;
    private int actualTeamID;
    private String name;

    public ConcreteUserBuilder() {}

    @Override
    public ConcreteUserBuilder setUserID(BasicID userID) {
        this.userID = userID;
        return this;
    }
    public ConcreteUserBuilder setUserID(int userID) {
        this.userID = new BasicID(userID);
        return this;
    }
    @Override
    public ConcreteUserBuilder setEmail(EMail email) {
        this.eMail = email;
        return this;
    }
    public ConcreteUserBuilder setEmail(String email) {
        this.eMail = new EMail(email);
        return this;
    }
    @Override
    public ConcreteUserBuilder setPassword(Password password) {
        this.password = password;
        return this;
    }
    public ConcreteUserBuilder setPassword(String password) {
        this.password = new Password(password);
        return this;
    }
    @Override
    public ConcreteUserBuilder setLoginToken(Token loginToken) {
        this.loginToken = loginToken;
        return this;
    }
    public ConcreteUserBuilder setLoginToken(String loginToken) {
        this.loginToken = new Token(loginToken);
        return this;
    }
    public ConcreteUserBuilder setNewLoginToken() {
        this.loginToken = new Token();
        return this;
    }
    @Override
    public ConcreteUserBuilder setRank(Rank rank) {
        this.rank = rank;
        return this;
    }
    @Override
    public ConcreteUserBuilder setActualTeamID(int teamID) {
        this.actualTeamID = teamID;
        return this;
    }

    @Override
    public ConcreteUserBuilder setUsername(Username username) {
        this.username = username;
        return this;
    }

    public ConcreteUserBuilder setUsername(String username) {
        this.username = new Username(username);
        return this;
    }

    public ConcreteUserBuilder setActualTeamID(UserRepository userRepository, int userID) {
        User user = new User();
        user.setUserID(new BasicID(userID));
        this.actualTeamID = userRepository.getCurrentTeam(userID).getTeamID().toInt();
        return this;
    }
    public ConcreteUserBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public User build() {
        User user = new User();
        user.setUserID(this.userID);
        user.setName(this.name);
        user.setUsername(this.username);
        user.seteMail(this.eMail);
        user.setPassword(this.password);
        user.setRank(this.rank);
        user.setLoginToken(this.loginToken);
        user.setActualTeamID(this.actualTeamID);
        return user;
    }
}
