package com.teampp.domain.entities;


import com.teampp.domain.entities.enums.Rank;
import com.teampp.domain.valueobjects.*;

import java.util.List;

public  class User {
    private BasicID userID;
    private EMail eMail;
    private Password password;
    private Username username;
    private Token loginToken;
    private Rank rank;
    private int actualTeamID;
    private String name;

    public User() {}

    public int getActualTeamID() {
        return actualTeamID;
    }

    public void setActualTeamID(int actualTeamID) {
        this.actualTeamID = actualTeamID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
