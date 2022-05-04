package com.teampp.usecase.team;

import android.widget.TextView;

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
    private final TextView pinError;

    public JoinTeam(UserTeamConnectionRepository repository, TeamRepository teamRepository, UserRepository userRepository, TextView pinError) {
        this.repository = repository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.pinError = pinError;
    }

    public boolean joinTeam(int userID, int teamID, int teamPin){
        UserTeamConnection connection = new UserTeamConnection(userID, teamID, Rank.PLAYER);
        User user = getUser(userID);
        Team team = getTeam(teamID, teamPin);
        user.setActualTeamID(team.getTeamID().toInt());

        if (!isInputValid(user, team)) {
            return false;
        }

        repository.addUserTeamConnection(connection.getTeamID(), connection.getUserID(), connection.getRank().rank);
        userRepository.changeCurrentTeam(user.getUserID().toInt(), user.getActualTeamID());
        return true;
    }

    private boolean isInputValid(User user, Team team) {
        if (userRepository.doesUserHasTeamConnection(user.getUserID().toInt(), team.getTeamID().toInt())) {
            pinError.setText("Sie sind bereits Mitglied des Teams!");
            return false;
        }
        if (!teamRepository.doesPinMatchTeam(team.getTeamID().toInt(), team.getPin())) {
            pinError.setText("Falscher Pin");
            return false;
        }
        return true;
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
