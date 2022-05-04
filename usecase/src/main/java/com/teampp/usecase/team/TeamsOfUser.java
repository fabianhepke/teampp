package com.teampp.usecase.team;

import com.teampp.domain.team.ConcreteTeamBuilder;
import com.teampp.domain.user.ConcreteUserBuilder;
import com.teampp.domain.team.Team;
import com.teampp.domain.user.UserRepository;
import com.teampp.domain.user.User;
import com.teampp.domain.team.TeamRepository;
import com.teampp.usecase.team.GetCurrentTeam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TeamsOfUser {
    private final TeamRepository repository;
    private final UserRepository userRepository;
    private List<Team> teams = new ArrayList<>();
    private GetCurrentTeam getCurrentTeam;

    public TeamsOfUser(TeamRepository repository, UserRepository userRepository, int userID) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.getCurrentTeam = getCurrentTeam;
        Team currentTeam = getCurrentTeam(userID);
        teams.add(currentTeam);

        User user = new ConcreteUserBuilder()
                .setUserID(userID)
                .build();
        String result = repository.getTeamsOfUser(user.getUserID().toInt());
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(result);
        } catch (JSONException e) {
            e.printStackTrace();
            jsonArray = null;
        }
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Team team = new ConcreteTeamBuilder().setTeamID(jsonObject.getInt("team_id"))
                        .setTeamname(jsonObject.getString("teamname"))
                        .build();
                team.setMembers(repository.getTeamMemberNum(team.getTeamID().toInt()));
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
        getCurrentTeam = new GetCurrentTeam(repository, userRepository, userID);
        return getCurrentTeam.getTeam();
    }

    public boolean isFinished() {
        if (teams.size() == 0) {
            return true;
        }
        return false;
    }
}
