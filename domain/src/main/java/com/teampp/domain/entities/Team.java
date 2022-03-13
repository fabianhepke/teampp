package com.teampp.domain.entities;

import com.teampp.domain.valueobjects.TeamID;

import java.util.Date;
import java.util.List;

public class Team {
    private TeamID teamID;
    private String teamName, description;
    private int pin, members;

    public Team() {
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public TeamID getTeamID() {
        return teamID;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setTeamID(TeamID teamID) {
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
}
