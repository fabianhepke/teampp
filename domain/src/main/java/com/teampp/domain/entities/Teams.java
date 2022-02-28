package com.teampp.domain.entities;

import com.teampp.domain.entities.valueobjects.TeamID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Teams {
    private final List<Team> teams = new ArrayList<Team>();

    public Teams(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            teams.add(new Team(new TeamID(jsonObject.getInt("team_id")), jsonObject.getString("teamname"), jsonObject.getInt("members")));
        }
    }

    public List<Team> getTeams() {
        return teams;
    }
}
