package com.teampp.domain.entities;

import com.teampp.domain.entities.valueobjects.*;

import java.util.Date;

public class TeamDate {
    private BasicID dateID;
    private String dateName;
    private int teamID;
    private Date date;
    private Adress adress;

    public TeamDate(BasicID dateID, String dateName, int teamID, Date date, Adress adress) {
        this.dateID = dateID;
        this.dateName = dateName;
        this.teamID = teamID;
        this.date = date;
        this.adress = adress;
    }

    public TeamDate(String dateName, int teamID, Date date, Adress adress) {
        this.dateName = dateName;
        this.teamID = teamID;
        this.date = date;
        this.adress = adress;
    }

    public TeamDate(int teamID, String dateName) {
        this.teamID = teamID;
        this.dateName = dateName;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public BasicID getDateID() {
        return dateID;
    }

    public void setDateID(BasicID dateID) {
        this.dateID = dateID;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public String getDateName() {
        return dateName;
    }

    public void setDateName(String dateName) {
        this.dateName = dateName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
