package com.teampp.usecase;

import com.teampp.domain.builder.ConcreteTeamBuilder;
import com.teampp.domain.builder.ConcreteUserBuilder;
import com.teampp.domain.entities.*;
import com.teampp.domain.entities.enums.Rank;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.domain.repositories.UserTeamConnectionRepository;
import com.teampp.domain.valueobjects.TeamID;

public class CreateTeam {

    private final TeamRepository repository;
    private final UserRepository userRepository;
    private final UserTeamConnectionRepository userTeamConnectionRepository;

    public CreateTeam(TeamRepository repository, UserRepository userRepository, UserTeamConnectionRepository userTeamConnectionRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.userTeamConnectionRepository = userTeamConnectionRepository;
    }

    public int getNewTeamID() {
        TeamID teamID = new TeamID(repository.getNewTeamID());
        return  teamID.toInt();
    }

    public void createTeam(int teamID, String teamname, String description, int pin, int userID) {
        User user = getUser(userID, teamID);
        Team team = getTeam(teamID, teamname, description, pin);
        UserTeamConnection userTeamConnection = new UserTeamConnection(userID, teamID, Rank.PLAYERADMIN);
        repository.registerTeam(team.getTeamID().toInt(), team.getTeamName(), team.getDescription(), team.getPin());
        userRepository.changeCurrentTeam(user.getUserID().toInt(), user.getActualTeamID());
        userTeamConnectionRepository.addUserTeamConnection(userTeamConnection.getTeamID(), userTeamConnection.getUserID(), userTeamConnection.getRank().rank);
    }

    private Team getTeam(int teamID, String teamname, String description, int pin) {
        return new ConcreteTeamBuilder()
                .setTeamID(teamID)
                .setTeamname(teamname)
                .setDescription(description)
                .setPin(pin)
                .build();
    }

    private User getUser(int userIDInt, int teamIDInt) {
        return new ConcreteUserBuilder()
                .setUserID(userIDInt)
                .setActualTeamID(teamIDInt)
                .build();
    }
}
