package com.teampp.usecase;

import com.teampp.domain.entities.UserTeamConnection;
import com.teampp.domain.entities.enums.Rank;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.domain.repositories.UserTeamConnectionRepository;

public class LeaveTeam {
    private UserTeamConnectionRepository userTeamConnectionRepository;
    private TeamRepository teamRepository;
    private UserRepository userRepository;

    public LeaveTeam(UserTeamConnectionRepository userTeamConnectionRepository, TeamRepository teamRepository, UserRepository userRepository) {
        this.userTeamConnectionRepository = userTeamConnectionRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public void leaveTeam(int userID, int teamID) {
        int currentTeamID = new GetCurrentTeam(teamRepository, userRepository, userID).getCurrentTeamID();
        UserTeamConnection userTeamConnection = new UserTeamConnection(userID, teamID, Rank.NORANK);
        userTeamConnectionRepository.removeUserTeamConnection(userTeamConnection);
        if (currentTeamID != teamID) {
            return;
        }
        changeCurrentTeam(userID);
    }

    private void changeCurrentTeam(int userID) {
        int teamID;
        GetTeamsOfUser getTeamsOfUser = new GetTeamsOfUser(teamRepository, userRepository, userID);
        getTeamsOfUser.nextTeam();
        teamID = getTeamsOfUser.getTeamId();
        ChangeCurrentTeam changeCurrentTeam = new ChangeCurrentTeam(userRepository);
        changeCurrentTeam.changeTeam(userID, teamID);
    }
}
