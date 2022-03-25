package com.teampp.domain.repositories;

import com.teampp.domain.entities.TeamDate;
import com.teampp.domain.valueobjects.TeamID;

import org.json.JSONArray;

public interface TeamDateRepository {
    void addHomeTeamDate(int teamID, String dateName, String dateString);

    TeamDate getDateByID(int dateID);

    JSONArray getDatesByTeamID(int teamID);

    void deleteTeamDate(int dateID);

    void addTeamDate(int teamID, String dateName, String dateString, int plz, String place, String street, String hnr);
}
