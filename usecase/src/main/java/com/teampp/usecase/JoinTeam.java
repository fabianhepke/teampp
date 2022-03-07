package com.teampp.usecase;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.teampp.domain.entities.Team;
import com.teampp.domain.entities.User;
import com.teampp.domain.entities.UserTeamConnection;
import com.teampp.domain.entities.enums.Rank;
import com.teampp.domain.factories.TeamFactory;
import com.teampp.domain.factories.UserFactory;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.domain.repositories.UserTeamConnectionRepository;

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

    public void joinTeam(int userID, int teamID, int teamPin){
        UserTeamConnection connection = new UserTeamConnection(userID, teamID, Rank.PLAYER);
        User user = getUser(userID);
        Team team = getTeam(teamID);
        team.setPin(teamPin);

        if (!isInputValid(user, team)) {
            return;
        }

        repository.addUserTeamConnection(connection);
        user.setActualTeamID(team.getTeamID().toInt());
        userRepository.changeCurrentTeam(user);
    }

    private boolean isInputValid(User user, Team team) {
        if (userRepository.doesUserHasTeamConnection(user, team)) {
            pinError.setText("Sie sind bereits Mitglied des Teams!");
            return false;
        }
        if (!teamRepository.doesPinMatchTeam(team)) {
            pinError.setText("Falscher Pin");
            return false;
        }
        return true;
    }

    private Team getTeam(int teamID) {
        TeamFactory teamFactory = new TeamFactory();
        return teamFactory.getTeam(teamID, teamRepository);
    }

    private User getUser(int userID) {
        UserFactory userFactory = new UserFactory();
        return userFactory.getUser(userID, userRepository);
    }
}
