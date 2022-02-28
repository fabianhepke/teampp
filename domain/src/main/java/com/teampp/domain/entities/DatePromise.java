package com.teampp.domain.entities;

public class DatePromise {
    private TeamDate teamDate;
    private User user;
    private boolean commitment;

    public DatePromise(TeamDate teamDate, User user, boolean commitment) {
        this.teamDate = teamDate;
        this.user = user;
        this.commitment = commitment;
    }

    public TeamDate getTeamDate() {
        return teamDate;
    }

    public void setTeamDate(TeamDate teamDate) {
        this.teamDate = teamDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isCommitment() {
        return commitment;
    }

    public void setCommitment(boolean commitment) {
        this.commitment = commitment;
    }
}
