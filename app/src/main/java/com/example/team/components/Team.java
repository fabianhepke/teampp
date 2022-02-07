package com.example.team.components;

import com.example.team.database.PhpConnection;

import java.util.Date;

public class Team {
    private TeamCode teamID;
    private String teamName, description;
    private Date creationDate;
    private int pin;

    public Team(TeamCode teamID, String teamName, String description, int pin) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.description = description;
        this.pin = pin;
    }

    public Team(TeamCode teamID, String teamName, String description) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.description = description;
    }

    public Team() {
        this.teamID = new TeamCode();
    }

    public TeamCode getTeamID() {
        return teamID;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setTeamID(TeamCode teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
