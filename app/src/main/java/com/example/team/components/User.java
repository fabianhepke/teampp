package com.example.team.components;

import com.example.team.help.Token;

import java.util.Date;

public  class User {
    int userID;
    String username, eMail, password;
    Token loginToken, verifyToken;
    Date creationDate;
    Rank rank;
    TeamCode teamID;
    boolean verfied;

    public User(int userID, String username, String eMail) {
        this.userID = userID;
        this.username = username;
        this.eMail = eMail;
    }

    public User() {
    }

    public User(int userID, String username, String eMail, Token loginToken) {
        this.userID = userID;
        this.username = username;
        this.eMail = eMail;
        this.loginToken = loginToken;
    }

    public User(String username, String eMail, String password) {
        this.username = username;
        this.eMail = eMail;
        this.password = password;
    }

    public TeamCode getTeamID() {
        return teamID;
    }

    public void setTeamID(TeamCode teamID) {
        this.teamID = teamID;
    }

    public boolean isVerfied() {
        return verfied;
    }

    public void setVerfied(boolean verfied) {
        this.verfied = verfied;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Token getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(Token loginToken) {
        this.loginToken = loginToken;
    }

    public Token getVerifyToken() {
        return verifyToken;
    }

    public void setVerifyToken(Token verifyToken) {
        this.verifyToken = verifyToken;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
