package com.example.team.database;

import android.util.Log;

import com.example.team.help.ApiHelper;
import com.teampp.domain.entities.UserTeamConnection;
import com.teampp.domain.repositories.UserTeamConnectionRepository;

import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

public class UserTeamConnectionRepositoryImpl implements UserTeamConnectionRepository {
    @Override
    public void addUserTeamConnection(UserTeamConnection userTeamConnection) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=addConnection&team_id="
                + userTeamConnection.getTeam().getTeamID().toInt()
                + "&user_id=" + userTeamConnection.getUser().getUserID().toInt()
                + "&rank=" + userTeamConnection.getUser().getRank();
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public void removeUserTeamConnection(UserTeamConnection userTeamConnection) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=removeConnection&team_id="
                + userTeamConnection.getTeam().getTeamID().toInt()
                + "&user_id=" + userTeamConnection.getUser().getUserID().toInt();
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public void updateRank(UserTeamConnection userTeamConnection) {
        //TO DO Implement
        String url ="https://www.memevz.h10.de/teamPP.php?op=updateConnection&team_id="
                + userTeamConnection.getTeam().getTeamID().toInt()
                + "&user_id=" + userTeamConnection.getUser().getUserID().toInt()
                + "&rank=" + userTeamConnection.getUser().getRank();
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }
}
