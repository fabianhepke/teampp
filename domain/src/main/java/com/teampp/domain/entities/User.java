package com.teampp.domain.entities;


import com.teampp.domain.entities.enums.Rank;
import com.teampp.domain.entities.valueobjects.*;

import java.util.Date;

public  class User {
    BasicID userID;
    EMail eMail;
    Password password;
    Username username;
    Token loginToken;
    Date creationDate;
    Rank rank;
    TeamID teamID;
    String name;

    public User(BasicID userID, Username username, EMail eMail) {
        this.userID = userID;
        this.username = username;
        this.eMail = eMail;
        this.rank = Rank.NORANK;
    }

    public User(BasicID userID, Username username, EMail eMail, Rank rank, TeamID teamID) {
        this.userID = userID;
        this.username = username;
        this.eMail = eMail;
        this.rank = rank;
        this.teamID = teamID;
    }

    public User(BasicID userID, Username username, EMail eMail, Rank rank) {
        this.userID = userID;
        this.username = username;
        this.eMail = eMail;
        this.rank = rank;
    }

    public User(String nameOrMail, String password) {
        if (EMail.isValid(nameOrMail)) {
            this.eMail = new EMail(nameOrMail);
        }else {
            this.username = new Username(nameOrMail);
        }
        this.password = new Password(password);
    }

    public User(BasicID userID) {
        this.userID = userID;
    }

    public User(BasicID userID, Username username, EMail eMail, Token loginToken) {
        this.userID = userID;
        this.username = username;
        this.eMail = eMail;
        this.loginToken = loginToken;
        this.rank = Rank.NORANK;
    }

    public User(Username username, EMail eMail, Password password) {
        this.username = username;
        this.eMail = eMail;
        this.password = password;
        this.rank = Rank.NORANK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamID getTeamID() {
        return teamID;
    }

    public void setTeamID(TeamID teamID) {
        this.teamID = teamID;
    }

    public BasicID getUserID() {
        return userID;
    }

    public void setUserID(BasicID userID) {
        this.userID = userID;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public EMail geteMail() {
        return eMail;
    }

    public void seteMail(EMail eMail) {
        this.eMail = eMail;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public Token getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(Token loginToken) {
        this.loginToken = loginToken;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
