package com.teampp.usecase.teamdate;

import android.widget.EditText;

import com.teampp.domain.team.ConcreteTeamDateBuilder;
import com.teampp.domain.teamdate.TeamDate;
import com.teampp.domain.teamdate.TeamDateRepository;
import com.teampp.domain.teamdate.Adress;

import java.util.Date;

public class CreateTeamDate {

    private final TeamDateRepository repository;
    private int plzInt;
    private String placeString, streetString, hnrString, titleString;

    public CreateTeamDate(TeamDateRepository repository) {
        this.repository = repository;
    }

    public void createTeamDate(Date date, int teamID, EditText title, EditText plz, EditText place, EditText street, EditText hnr) {
        assignData(plz, place, street, hnr, title);
        if (!isInputValid(title, plz, place, street, hnr)) {
            return;
        }
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
