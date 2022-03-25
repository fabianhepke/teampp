package com.teampp.usecase;

import com.teampp.domain.entities.UserTeamConnection;
import com.teampp.domain.entities.enums.Rank;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.domain.repositories.UserTeamConnectionRepository;

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
        TeamsOfUser teamsOfUser = new TeamsOfUser(teamRepository, userRepository, userID);
        teamsOfUser.nextTeam();
        teamID = teamsOfUser.getTeamId();
        ChangeCurrentTeam changeCurrentTeam = new ChangeCurrentTeam(userRepository);
        changeCurrentTeam.changeTeam(userID, teamID);
    }
}
