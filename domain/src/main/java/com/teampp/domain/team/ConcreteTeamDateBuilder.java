package com.teampp.domain.team;

import com.teampp.domain.general.valueobjects.BasicID;
import com.teampp.domain.teamdate.Adress;
import com.teampp.domain.teamdate.DateConverter;
import com.teampp.domain.teamdate.TeamDate;

import java.util.Date;

public class ConcreteTeamDateBuilder implements TeamDateBuilder{
    private BasicID dateID;
    private String dateName, dateString;
    private int teamID;
    private Date date;
    private Adress adress;

    public ConcreteTeamDateBuilder(){}

    @Override
    public ConcreteTeamDateBuilder setDateID(BasicID dateID) {
        this.dateID = dateID;
        return this;
    }
    public ConcreteTeamDateBuilder setDateID(int dateID) {
        this.dateID = new BasicID(dateID);
        return this;
    }
    @Override
    public ConcreteTeamDateBuilder setDateName(String dateName) {
        this.dateName = dateName;
        return this;
    }
    @Override
    public ConcreteTeamDateBuilder setTeamID(int teamID) {
        this.teamID = teamID;
        return this;
    }
    @Override
    public ConcreteTeamDateBuilder setDate(Date date) {
        this.date = date;
        this.dateString = DateConverter.convertDateToString(date);
        return this;
    }
    @Override
    public ConcreteTeamDateBuilder setAdress(Adress adress) {
        this.adress = adress;
        return this;
    }

    @Override
    public ConcreteTeamDateBuilder setDateString(String dateString) {
        this.dateString = dateString;
        this.date = DateConverter.convertStringToDate(dateString);
        return this;
    }

    public TeamDate build() {
        TeamDate teamDate = new TeamDate();
        teamDate.setDateID(this.dateID);
        teamDate.setTeamID(this.teamID);
        teamDate.setDateName(this.dateName);
        teamDate.setAdress(this.adress);
        teamDate.setDate(this.date);
        return teamDate;
    }

}
