package com.teampp.domain.entities;

import com.teampp.domain.domainservice.DateConverter;
import com.teampp.domain.valueobjects.*;

import java.util.Date;

public class TeamDate {
    private BasicID dateID;
    private String dateName, dateString;
    private int teamID;
    private Date date;
    private Adress adress;

    public TeamDate() {
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
        this.date = DateConverter.convertStringToDate(dateString);
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
        this.dateString = DateConverter.convertDateToString(date);
    }
}
