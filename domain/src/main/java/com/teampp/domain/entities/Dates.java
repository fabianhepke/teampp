package com.teampp.domain.entities;

import com.teampp.domain.entities.valueobjects.Adress;
import com.teampp.domain.entities.valueobjects.BasicID;
import com.teampp.domain.entities.valueobjects.TeamID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dates {
    private List<TeamDate> dates = new ArrayList<TeamDate>();

    public Dates(String jsonString) throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            dates.add(new TeamDate(new BasicID(jsonObject.getInt("date_id")),
                    jsonObject.getString("datename"),
                    new TeamID(jsonObject.getInt("team_id")),
                    new Date(jsonObject.getLong("date")),
                    new Adress(jsonObject.getInt("plz"), jsonObject.getString("place"),
                            jsonObject.getString("street"), jsonObject.getString("hnr"))));
        }
    }

    public List<TeamDate> getDates() {
        return dates;
    }
}
