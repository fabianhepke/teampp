package com.teampp.domain.repositories;

import com.teampp.domain.entities.Dates;
import com.teampp.domain.entities.TeamDate;
import com.teampp.domain.entities.valueobjects.TeamID;

public interface TeamDateRepository {
    void addTeamDate(TeamDate teamDate, String date);

    Dates getDatesByTeamID(TeamID teamID);

    void deleteTeamDate(TeamDate teamDate);

    void addHomeTeamDate(TeamDate teamDate, String date);
}
