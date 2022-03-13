package com.teampp.usecase;

import com.teampp.domain.builder.ConcreteTeamBuilder;
import com.teampp.domain.builder.ConcreteUserBuilder;
import com.teampp.domain.entities.Team;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.domain.entities.User;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.valueobjects.BasicID;
import com.teampp.domain.valueobjects.TeamID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetTeamsOfUser {
    private final TeamRepository repository;
    private final UserRepository userRepository;
    private List<Team> teams = new ArrayList<>();

    public GetTeamsOfUser(TeamRepository repository, UserRepository userRepository, int userID) {
        this.repository = repository;
        this.userRepository = userRepository;

        Team currentTeam = getCurrentTeam(userID);
        teams.add(currentTeam);

        User user = new ConcreteUserBuilder()
                .setUserID(userID)
                .build();
        JSONArray jsonArray = repository.getTeamsOfUser(user);
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Team team = new ConcreteTeamBuilder().setTeamID(jsonObject.getInt("team_id"))
                        .setTeamname(jsonObject.getString("teamname"))
                        .setMembers(repository, jsonObject.getInt("team_id"))
                        .build();
                team.setMembers(repository.getTeamMemberNum(team));
                if (team.getTeamID().toInt() != currentTeam.getTeamID().toInt()) {
                    teams.add(team);
                }
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void nextTeam() {
        teams.remove(0);
    }

    public int getTeamId() {
        return teams.get(0).getTeamID().toInt();
    }

    public String getTeamName() {
        return teams.get(0).getTeamName();
    }

    public int getTeamMemberNum() {
        return teams.get(0).getMembers();
    }

    public Team getCurrentTeam(int userID) {
        GetCurrentTeam getCurrentTeam = new GetCurrentTeam(repository, userRepository, userID);
        return getCurrentTeam.getTeam();
    }

    public boolean isFinished() {
        if (teams.size() == 0) {
            return true;
        }
        return false;
    }
}
