package com.example.team.database;

import android.util.Log;

import com.example.team.help.ApiHelper;
import com.teampp.domain.entities.UserTeamConnection;
import com.teampp.domain.repositories.UserTeamConnectionRepository;

import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

public class UserTeamConnectionRepositoryImpl implements UserTeamConnectionRepository {
    @Override
    public void addUserTeamConnection(int teamID, int userID, String rank) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=addConnection&team_id="
                + teamID
                + "&user_id=" + userID
                + "&rank=" + rank;
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public void removeUserTeamConnection(int teamID, int userID) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=removeConnection&team_id="
                + teamID
                + "&user_id=" + userID;
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public void updateRank(int teamID, int userID, String rank) {
        //TO DO Implement
        String url ="https://www.memevz.h10.de/teamPP.php?op=updateConnection&team_id="
                + teamID
                + "&user_id=" + userID
                + "&rank=" + rank;
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }
}
