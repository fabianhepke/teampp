package com.teampp.usecase;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.teampp.domain.entities.*;
import com.teampp.domain.entities.enums.Rank;
import com.teampp.domain.entities.valueobjects.BasicID;
import com.teampp.domain.factories.TeamFactory;
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

    public void createTeam(int teamID, String teamname, String description, int pin, int userID) {
        User user = getUser(userID, teamID);
        Team team = getTeam(teamID, teamname, description, pin);
        repository.registerTeam(team);
        userRepository.changeCurrentTeam(user);
        userTeamConnectionRepository.addUserTeamConnection(new UserTeamConnection(userID, teamID, Rank.PLAYERADMIN));
    }

    private Team getTeam(int teamID, String teamname, String description, int pin) {
        TeamFactory teamFactory = new TeamFactory();
        return teamFactory.getTeam(teamID, teamname, description, pin);
    }

    private User getUser(int userIDInt, int teamIDInt) {
        User user = new User(new BasicID(userIDInt));
        user.setActualTeamID(teamIDInt);
        return user;
    }




}
