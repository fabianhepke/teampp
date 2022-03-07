package com.teampp.usecase;

import android.content.Intent;
import android.widget.EditText;

import com.teampp.domain.entities.TeamDate;
import com.teampp.domain.entities.valueobjects.Adress;
import com.teampp.domain.factories.TeamDateFactory;
import com.teampp.domain.repositories.TeamDateRepository;
import com.teampp.usecase.help.DateConverter;

import java.util.Date;

public class CreateTeamDate {

    private final TeamDateRepository repository;
    private int plzInt;
    private String placeString, streetString, hnrString, titleString;

    public CreateTeamDate(TeamDateRepository repository) {
        this.repository = repository;
    }

    public void createHomeTeamDate(int teamID, Date date, EditText title) {
        if (title.getText().toString().equals("")) {
            title.setError("Titel muss gesetzt sein");
            return;
        }
        TeamDate teamDate = getHomeTeamDate(teamID, date, title.getText().toString());
        String dateString = DateConverter.convertDateToString(date);
        repository.addHomeTeamDate(teamDate, dateString);
    }

    private TeamDate getHomeTeamDate(int teamID, Date date, String datename) {
        TeamDateFactory teamDateFactory = new TeamDateFactory();
        return teamDateFactory.getHomeTeamDate(teamID, datename, date);
    }

    public void createTeamDate(Date date, int teamID, EditText title, EditText plz, EditText place, EditText street, EditText hnr) {
        assignData(plz, place, street, hnr, title);
        if (!isInputValid(title, plz, place, street, hnr)) {
            return;
        }
        TeamDate teamDate = getTeamDate(teamID, title.getText().toString(), date, plz.getText().toString(), place.getText().toString(), street.getText().toString(), hnr.getText().toString());
        String dateString = DateConverter.convertDateToString(date);
        repository.addTeamDate(teamDate, dateString);
    }

    private TeamDate getTeamDate(int teamID, String title, Date date, String plz, String place, String street, String hnr) {
        TeamDateFactory teamDateFactory = new TeamDateFactory();
        return teamDateFactory.getTeamDate(teamID, title, date, Integer.parseInt(plz), place, street, hnr);
    }

    private boolean isInputValid(EditText title, EditText plz, EditText place, EditText street, EditText hnr) {
        if (titleString.equals("")) {
            title.setError("Titel muss ausgefüllt werden!");
            return false;
        }
        if (placeString.equals("")) {
            place.setError("Ort muss angegeben werden!");
            return false;
        }
        if (streetString.equals("")) {
            street.setError("Straße muss angegeben werden!");
            return false;
        }
        if (String.valueOf(plzInt).length() != 5) {
            plz.setError("Ungültige Postleitzahl!");
            return false;
        }
        if (hnrString.equals("")) {
            hnr.setError("Hausnummer muss angegeben werden!");
        }
        return true;
    }

    private void assignData(EditText plz, EditText place, EditText street, EditText hnr, EditText title) {
        plzInt = Integer.parseInt(plz.getText().toString());
        placeString = place.getText().toString();
        streetString = street.getText().toString();
        hnrString = hnr.getText().toString();
        titleString = title.getText().toString();
    }
}
