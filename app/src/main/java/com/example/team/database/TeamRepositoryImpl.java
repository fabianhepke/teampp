package com.example.team.database;

import android.util.Log;

import com.example.team.help.ApiHelper;
import com.example.team.help.URLHelper;
import com.teampp.domain.entities.Team;
import com.teampp.domain.entities.Teams;
import com.teampp.domain.entities.User;
import com.teampp.domain.entities.valueobjects.BasicID;
import com.teampp.domain.entities.valueobjects.TeamID;
import com.teampp.domain.repositories.TeamRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

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
    public void registerTeam(Team team) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=registerTeam&teamname="
                + team.getTeamName()
                + "&description=" + team.getDescription()
                + "&team_id=" + team.getTeamID().toInt()
                + "&pin=" + team.getPin();
        url = URLHelper.convertStringForUrl(url);
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public boolean doesPinMatchTeam(Team team) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=doesPinMatchTeam&team_id="
                + team.getTeamID().toInt()
                + "&pin=" + team.getPin();
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
    public int getTeamMemberNum(Team team) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=getTeamMemberNum&team_id=" + team.getTeamID().toInt();
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
    public String getTeamName(TeamID teamID) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=getTeamname&team_id=" + teamID.toInt();
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
    public Teams getTeamsOfUser(User user) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=getTeamsOfUser&user_id=" + user.getUserID().toInt();
        String result;
        try {
            result = new ApiHelper(url).execute().get();
            return new Teams(URLHelper.convertUrlString(result));
        }catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
