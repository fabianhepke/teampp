package com.teampp.usecase.team;

import com.teampp.domain.team.ConcreteTeamBuilder;
import com.teampp.domain.user.ConcreteUserBuilder;
import com.teampp.domain.team.Team;
import com.teampp.domain.user.User;
import com.teampp.domain.userteamconnection.UserTeamConnection;
import com.teampp.domain.user.Rank;
import com.teampp.domain.team.TeamRepository;
import com.teampp.domain.user.UserRepository;
import com.teampp.domain.userteamconnection.UserTeamConnectionRepository;

public class JoinTeam {
    private final UserTeamConnectionRepository repository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public JoinTeam(UserTeamConnectionRepository repository, TeamRepository teamRepository, UserRepository userRepository) {
        this.repository = repository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public void joinTeam(int userID, int teamID, int teamPin){
        UserTeamConnection connection = new UserTeamConnection(userID, teamID, Rank.PLAYER);
        User user = getUser(userID);
        Team team = getTeam(teamID, teamPin);
        user.setActualTeamID(team.getTeamID().toInt());

        repository.addUserTeamConnection(connection.getTeamID(), connection.getUserID(), connection.getRank().rank);
        userRepository.changeCurrentTeam(user.getUserID().toInt(), user.getActualTeamID());
    }

    private Team getTeam(int teamID, int teamPin) {
        return new ConcreteTeamBuilder()
                .setTeamID(teamID)
                .setPin(teamPin)
                .build();
    }

    private User getUser(int userID) {
        return new ConcreteUserBuilder()
                .setUserID(userID)
                .build();
    }
}
