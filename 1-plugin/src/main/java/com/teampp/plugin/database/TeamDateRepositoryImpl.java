package com.teampp.plugin.database;

import com.teampp.plugin.help.ApiHelper;
import com.teampp.plugin.help.URLHelper;
import com.teampp.domain.teamdate.TeamDateRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class TeamDateRepositoryImpl implements TeamDateRepository {

    @Override
    public JSONArray getDatesByTeamID(int teamID) {
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
    public String getDatenameByID(int dateID) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=getDatenameById&date_id=" + dateID;
        String result;
        try {
            result = new ApiHelper(url).execute().get();
            JSONObject json = new JSONObject(URLHelper.convertUrlString(result));
            return json.getString("datename");
        }catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
