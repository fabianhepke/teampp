package com.teampp.domain.teamdate;

import org.json.JSONArray;

public interface TeamDateRepository {

    String getDatenameByID(int dateID);

    JSONArray getDatesByTeamID(int teamID);

    void deleteTeamDate(int dateID);

    void addTeamDate(int teamID, String dateName, String dateString, int plz, String place, String street, String hnr);
}
