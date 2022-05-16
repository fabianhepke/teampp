package com.teampp.domain.team;

import com.teampp.domain.general.valueobjects.BasicID;
import com.teampp.domain.teamdate.Adress;
import com.teampp.domain.teamdate.TeamDate;

import java.util.Date;

public interface TeamDateBuilder {
    TeamDateBuilder setDateID(BasicID dateID);

    TeamDateBuilder setDateName(String dateName);

    TeamDateBuilder setTeamID(int teamID);

    TeamDateBuilder setDate(Date date);

    TeamDateBuilder setAdress(Adress adress);

    TeamDateBuilder setDateString(String dateString);

    TeamDate build();
}
