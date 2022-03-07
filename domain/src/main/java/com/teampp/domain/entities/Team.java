package com.teampp.domain.entities;

import com.teampp.domain.entities.valueobjects.TeamID;

import java.util.Date;
import java.util.List;

public class Team {
    private TeamID teamID;
    private String teamName, description;
    private Date creationDate;
    private int pin, members;
    private List<Integer> dateIDs;

    public Team(TeamID teamID, String teamName, String description, int pin) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.description = description;
        this.pin = pin;
    }

    public Team(TeamID teamID, String teamName, String description, int pin, int members) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.description = description;
        this.pin = pin;
        this.members = members;
    }

    public Team(TeamID teamID, String teamName, int members) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.members = members;
    }

    public Team(TeamID teamID, String teamName, String description) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.description = description;
    }

    public Team(TeamID teamID) {
        this.teamID = teamID;
    }

    public Team(TeamID team_id, String teamname) {
    }

    public Team(TeamID team_id, int pin) {
        this.teamID = team_id;
        this.pin = pin;
    }

    public List<Integer> getDateIDs() {
        return dateIDs;
    }

    public void setDateIDs(List<Integer> dateIDs) {
        this.dateIDs = dateIDs;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
