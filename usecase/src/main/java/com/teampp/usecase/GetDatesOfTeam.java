package com.teampp.usecase;

import com.teampp.domain.entities.TeamDate;
import com.teampp.domain.builder.ConcreteTeamDateBuilder;
import com.teampp.domain.repositories.TeamDateRepository;
import com.teampp.domain.valueobjects.Adress;
import com.teampp.domain.valueobjects.TeamID;
import com.teampp.domain.domainservice.DateConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetDatesOfTeam {
    private final List<TeamDate> dates = new ArrayList<>();

    public GetDatesOfTeam(TeamDateRepository teamDateRepository, int teamID) {
        JSONArray jsonArray = teamDateRepository.getDatesByTeamID(new TeamID(teamID));
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                TeamDate date = new ConcreteTeamDateBuilder().setDateID(jsonObject.getInt("date_id"))
                        .setDateName(jsonObject.getString("datename"))
                        .setDate(DateConverter.convertStringToDate(jsonObject.getString("date")))
                        .setAdress(new Adress(jsonObject.getInt("plz"), jsonObject.getString("place"), jsonObject.getString("street"), jsonObject.getString("hnr")))
                        .build();
                dates.add(date);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getHnr() {
        return dates.get(0).getAdress().getHouseNr();
    }

    public String getStreet() {
        return dates.get(0).getAdress().getStreet();
    }

    public String getPlace() {
        return dates.get(0).getAdress().getPlace();
    }

    public int getPLZ() {
        return dates.get(0).getAdress().getPlz();
    }

    public Date getDate() {
        return dates.get(0).getDate();
    }

    public String getDateName() {
        return dates.get(0).getDateName();
    }

    public int getDateID() {
        return dates.get(0).getDateID().toInt();
    }

    public void nextDate() {
        dates.remove(0);
    }

    public boolean isFinished() {
        if (dates.size() == 0) {
            return true;
        }
        return false;
    }
}
