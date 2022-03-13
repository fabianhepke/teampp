package com.teampp.domain.builder;

import com.teampp.domain.entities.TeamDate;
import com.teampp.domain.valueobjects.Adress;
import com.teampp.domain.valueobjects.BasicID;

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
