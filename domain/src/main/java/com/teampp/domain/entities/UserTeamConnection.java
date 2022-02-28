package com.teampp.domain.entities;

import com.teampp.domain.entities.enums.Rank;

public class UserTeamConnection {
    private User user;
    private Team team;

    public UserTeamConnection(User user, Team team) {
        this.user = user;
        this.team = team;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
