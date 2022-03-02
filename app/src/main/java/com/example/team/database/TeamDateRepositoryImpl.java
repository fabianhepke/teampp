package com.example.team.database;

import com.example.team.help.ApiHelper;
import com.example.team.help.URLHelper;
import com.teampp.domain.entities.Dates;
import com.teampp.domain.entities.TeamDate;
import com.teampp.domain.entities.Teams;
import com.teampp.domain.entities.valueobjects.TeamID;
import com.teampp.domain.repositories.TeamDateRepository;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class TeamDateRepositoryImpl implements TeamDateRepository {

    @Override
    public void addHomeTeamDate(TeamDate teamDate, String date) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=addHomeDate&team_id="
                + teamDate.getTeamID().toInt()
                + "&place=Daheim"
                + "&datename=" + teamDate.getDateName()
                + "&date=" + date;
        url = URLHelper.convertStringForUrl(url);
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public Dates getDatesByTeamID(TeamID teamID) {
        //TO DO Implement
        String url = "https://www.memevz.h10.de/teamPP.php?op=getDatesOfTeam&user_id=" + teamID.toInt();
        String result;
        try {
            result = new ApiHelper(url).execute().get();
            return new Dates(URLHelper.convertUrlString(result));
        }catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteTeamDate(TeamDate teamDate) {
        //TO DO Implement
        String url ="https://www.memevz.h10.de/teamPP.php?op=delteDate&team_id="
                + teamDate.getTeamID().toInt()
                + "&plz=" + teamDate.getAdress().getPlz()
                + "&place=" + teamDate.getAdress().getPlace()
                + "&street=" + teamDate.getAdress().getStreet()
                + "&hnr=" + teamDate.getAdress().getHouseNr()
                + "&datename=" + teamDate.getDateName()
                + "&date=" + teamDate.getDate();
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public void addTeamDate(TeamDate teamDate, String date) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=addDate&team_id="
                + teamDate.getTeamID().toInt()
                + "&plz=" + teamDate.getAdress().getPlz()
                + "&place=" + teamDate.getAdress().getPlace()
                + "&street=" + teamDate.getAdress().getStreet()
                + "&hnr=" + teamDate.getAdress().getHouseNr()
                + "&datename=" + teamDate.getDateName()
                + "&date=" + date;
        url = URLHelper.convertStringForUrl(url);
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }
}
