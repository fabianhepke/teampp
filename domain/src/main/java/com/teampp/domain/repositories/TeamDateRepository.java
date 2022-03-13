package com.teampp.domain.repositories;

import com.teampp.domain.entities.TeamDate;
import com.teampp.domain.valueobjects.TeamID;

import org.json.JSONArray;

public interface TeamDateRepository {
    void addTeamDate(TeamDate teamDate, String date);

    TeamDate getDateByID(int dateID);

    JSONArray getDatesByTeamID(TeamID teamID);

    void deleteTeamDate(TeamDate teamDate);

    void addHomeTeamDate(TeamDate teamDate, String date);
}
