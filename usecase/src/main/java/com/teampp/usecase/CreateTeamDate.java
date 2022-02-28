package com.teampp.usecase;

import com.teampp.domain.entities.TeamDate;
import com.teampp.domain.repositories.TeamDateRepository;

public class CreateTeamDate {

    private final TeamDateRepository repository;

    public CreateTeamDate(TeamDateRepository repository) {
        this.repository = repository;
    }

    public void createTeamDate(TeamDate teamDate) {
        repository.addTeamDate(teamDate);
    }
}
