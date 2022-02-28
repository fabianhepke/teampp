package com.teampp.usecase;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.teampp.domain.entities.Team;
import com.teampp.domain.entities.User;
import com.teampp.domain.entities.UserTeamConnection;
import com.teampp.domain.entities.enums.Rank;
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

    public void joinTeam(User user, Team team){
        UserTeamConnection connection = new UserTeamConnection(user, team);
        if (user.getUserID() == null || user.getRank() == Rank.NORANK || user.getRank() == null || team.getTeamID() == null){
            return;
        }
        if (userRepository.doesUserHasTeamConnection(user, team)) {
            pinError.setText("Sie sind bereits Mitglied des Teams!");
            return;
        }
        if (!teamRepository.doesPinMatchTeam(team)) {
            pinError.setText("Falscher Pin");
            return;
        }
        repository.addUserTeamConnection(connection);
        if (user.getTeamID() == null) {
            return;
        }
        user.setTeamID(team.getTeamID());
        userRepository.changeCurrentTeam(user);
    }
}
