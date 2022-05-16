package com.teampp.plugin.database;

import com.teampp.plugin.help.ApiHelper;
import com.teampp.plugin.help.URLHelper;
import com.teampp.domain.team.Team;
import com.teampp.domain.team.TeamRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class TeamRepositoryImpl implements TeamRepository {

    @Override
    public int getNewTeamID() {
        String url = "https://www.memevz.h10.de/teamPP.php?op=maxTeamID";
        String result;
        try {
            result = new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            result = null;
        }
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            return (jsonObject.getInt("team_id") + 1);
        }catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void registerTeam(int teamID, String teamname, String description, int pin) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=registerTeam&teamname="
                + teamname
                + "&description=" + description
                + "&team_id=" + teamID
                + "&pin=" + pin;
        url = URLHelper.convertStringForUrl(url);
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public boolean doesPinMatchTeam(int teamID, int pin) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=doesPinMatchTeam&team_id="
                + teamID
                + "&pin=" + pin;
        String result;
        try {
            result = new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            result = null;
        }
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            return jsonObject.getBoolean("result");
        }catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getTeamMemberNum(int teamID) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=getTeamMemberNum&team_id=" + teamID;
        String result;
        try {
            result = new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            result = null;
        }
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            return jsonObject.getInt("members");
        }catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public String getTeamName(int teamID) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=getTeamname&team_id=" + teamID;
        String result;
        try {
            result = new ApiHelper(url).execute().get();
            result = URLHelper.convertUrlString(result);
        }catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            result = null;
        }
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            return jsonObject.getString("teamname");
        }catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getTeamsOfUser(int userID) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=getTeamsOfUser&user_id=" + userID;
        String result;
        try {
            result = new ApiHelper(url).execute().get();
            return URLHelper.convertUrlString(result);
        }catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Team getTeamByID(int teamID) {
        //TODO
        return null;
    }
}
