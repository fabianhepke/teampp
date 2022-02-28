package com.teampp.usecase;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.teampp.domain.entities.*;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.domain.repositories.UserTeamConnectionRepository;

public class CreateTeam {

    private final TeamRepository repository;
    private final UserRepository userRepository;
    private final UserTeamConnectionRepository userTeamConnectionRepository;

    public CreateTeam(TeamRepository repository, UserRepository userRepository, UserTeamConnectionRepository userTeamConnectionRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.userTeamConnectionRepository = userTeamConnectionRepository;
    }

    public void createTeam(Team team, User user) {
        if (team.getTeamID() == null) {
            return;
        }
        repository.registerTeam(team);
        user.setTeamID(team.getTeamID());
        if (user.getTeamID() == null) {
            return;
        }
        userRepository.changeCurrentTeam(user);
        userTeamConnectionRepository.addUserTeamConnection(new UserTeamConnection(user, team));
    }


}
