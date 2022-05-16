package com.teampp.usecase.team;

import com.teampp.domain.userteamconnection.UserTeamConnection;
import com.teampp.domain.user.Rank;
import com.teampp.domain.team.TeamRepository;
import com.teampp.domain.user.UserRepository;
import com.teampp.domain.userteamconnection.UserTeamConnectionRepository;
import com.teampp.usecase.user.ChangeCurrentTeamOfUser;

public class LeaveTeam {
    private final UserTeamConnectionRepository userTeamConnectionRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public LeaveTeam(UserTeamConnectionRepository userTeamConnectionRepository, TeamRepository teamRepository, UserRepository userRepository) {
        this.userTeamConnectionRepository = userTeamConnectionRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public void leaveTeam(int userID, int teamID) {
        int currentTeamID = new GetCurrentTeam(teamRepository, userRepository, userID).getCurrentTeamID();
        UserTeamConnection userTeamConnection = new UserTeamConnection(userID, teamID, Rank.NORANK);
        userTeamConnectionRepository.removeUserTeamConnection(userTeamConnection.getTeamID(), userTeamConnection.getUserID());
        if (currentTeamID != teamID) {
            return;
        }
        changeCurrentTeam(userID);
    }

    private void changeCurrentTeam(int userID) {
        int teamID;
        ChangeCurrentTeamOfUser changeCurrentTeam = new ChangeCurrentTeamOfUser(userRepository);
        try {
            TeamsOfUser teamsOfUser = new TeamsOfUser(teamRepository, userRepository, userID);
            teamsOfUser.nextTeam();
            teamID = teamsOfUser.getTeamId();
            changeCurrentTeam.changeTeam(userID, teamID);
        } catch (Exception e) {
            changeCurrentTeam.changeTeam(userID, 0);
        }

    }
}
