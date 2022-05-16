package com.teampp.usecase.teamdate;

import android.widget.EditText;

import com.teampp.domain.team.ConcreteTeamDateBuilder;
import com.teampp.domain.teamdate.TeamDate;
import com.teampp.domain.teamdate.TeamDateRepository;
import com.teampp.domain.teamdate.Adress;

import java.util.Date;

public class CreateTeamDate {

    private final TeamDateRepository repository;

    public CreateTeamDate(TeamDateRepository repository) {
        this.repository = repository;
    }

    public void createTeamDate(Date date, int teamID, EditText title, EditText plz, EditText place, EditText street, EditText hnr) {

        TeamDate teamDate = getTeamDate(teamID, title.getText().toString(), date, plz.getText().toString(), place.getText().toString(), street.getText().toString(), hnr.getText().toString());
        repository.addTeamDate(teamDate.getTeamID(),
                teamDate.getDateName(),
                teamDate.getDateString(),
                teamDate.getAdress().getPlz(),
                teamDate.getAdress().getPlace(),
                teamDate.getAdress().getStreet(),
                teamDate.getAdress().getHouseNr());
    }

    private TeamDate getTeamDate(int teamID, String title, Date date, String plz, String place, String street, String hnr) {
        Adress adress = new Adress(Integer.parseInt(plz), place, street, hnr);
        return new ConcreteTeamDateBuilder()
                .setDate(date)
                .setTeamID(teamID)
                .setDateName(title)
                .setAdress(adress)
                .build();
    }
}
