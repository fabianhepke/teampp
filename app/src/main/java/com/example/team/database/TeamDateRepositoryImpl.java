package com.example.team.database;

import com.example.team.help.ApiHelper;
import com.example.team.help.URLHelper;
import com.teampp.domain.builder.ConcreteTeamDateBuilder;
import com.teampp.domain.entities.TeamDate;
import com.teampp.domain.valueobjects.Adress;
import com.teampp.domain.valueobjects.TeamID;
import com.teampp.domain.repositories.TeamDateRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class TeamDateRepositoryImpl implements TeamDateRepository {

    @Override
    public void addHomeTeamDate(int teamID, String dateName, String dateString) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=addHomeDate&team_id="
                + teamID
                + "&place=Daheim"
                + "&datename=" + dateName
                + "&date=" + dateString;
        url = URLHelper.convertStringForUrl(url);
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public JSONArray getDatesByTeamID(int teamID) {
        //TODO Implement
        String url = "https://www.memevz.h10.de/teamPP.php?op=getDatesOfTeam&team_id=" + teamID;
        String result;
        try {
            result = new ApiHelper(url).execute().get();
            return new JSONArray(URLHelper.convertUrlString(result));
        }catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteTeamDate(int dateID) {
        //TODO Implement
        String url ="https://www.memevz.h10.de/teamPP.php?op=delteDate&team_id="
                + dateID;
        try {
            new ApiHelper(url).execute().get();

        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public void addTeamDate(int teamID, String dateName, String dateString, int plz, String place, String street, String hnr) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=addDate&team_id="
                + teamID
                + "&plz=" + plz
                + "&place=" + place
                + "&street=" + street
                + "&hnr=" + hnr
                + "&datename=" + dateName
                + "&date=" + dateString;
        url = URLHelper.convertStringForUrl(url);
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public TeamDate getDateByID(int dateID) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=getDateById&date_id=" + dateID;
        String result;
        try {
            result = new ApiHelper(url).execute().get();
            JSONObject json = new JSONObject(URLHelper.convertUrlString(result));
            return new ConcreteTeamDateBuilder().setDateID(dateID)
                    .setDateName(json.getString("datename"))
                    .setDateString(json.getString("date"))
                    .setAdress(new Adress(json.getInt("plz"), json.getString("place"), json.getString("street"), json.getString("hnr")))
                    .build();
        }catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
