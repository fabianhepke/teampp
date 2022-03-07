package com.teampp.domain.entities;

import com.teampp.domain.entities.enums.Rank;

public class UserTeamConnection {
    private int userID, teamID;
    private Rank rank;

    public UserTeamConnection(int userID, int teamID, Rank rank) {
        this.userID = userID;
        this.teamID = teamID;
        this.rank = rank;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
