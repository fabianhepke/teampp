package com.teampp.usecase;

import android.widget.EditText;

import com.teampp.domain.entities.TeamDate;
import com.teampp.domain.entities.valueobjects.Adress;
import com.teampp.domain.repositories.TeamDateRepository;

public class CreateTeamDate {

    private final TeamDateRepository repository;
    private int plzInt;
    private String placeString, streetString, hnrString, titleString;

    public CreateTeamDate(TeamDateRepository repository) {
        this.repository = repository;
    }

    public void createHomeTeamDate(TeamDate teamDate, String date, EditText title) {
        if (title.getText().toString().equals("")) {
            title.setError("Titel muss gesetzt sein");
            return;
        }
        teamDate.setDateName(title.getText().toString());
        repository.addHomeTeamDate(teamDate, date);
    }

    public void createTeamDate(TeamDate teamDate, String date, EditText title, EditText plz, EditText place, EditText street, EditText hnr) {
        if (teamDate.getTeamID() == null) {
            return;
        }
        assignData(plz, place, street, hnr, title);

        if (!isInputValid(title, plz, place, street, hnr)) {
            return;
        }
        teamDate = prepareTeamDate(teamDate);

        repository.addTeamDate(teamDate, date);
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

    private TeamDate prepareTeamDate(TeamDate teamDate) {
        teamDate.setDateName(titleString);
        teamDate.setAdress(new Adress(plzInt, placeString, streetString, hnrString));
        return teamDate;
    }
}
