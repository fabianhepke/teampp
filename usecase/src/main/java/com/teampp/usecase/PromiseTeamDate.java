package com.teampp.usecase;

import com.teampp.domain.entities.DatePromise;
import com.teampp.domain.repositories.DatePromiseRepository;

public class PromiseTeamDate {

    private final DatePromiseRepository repository;

    public PromiseTeamDate(DatePromiseRepository repository) {
        this.repository = repository;
    }

    public void commitTeamDate(DatePromise commitment) {
        repository.addDatePromise(commitment);
    }

    public boolean doesUserCommited(DatePromise commitment) {
        return repository.doesUserPromisedDate(commitment);
    }

    public void changeCommitment(DatePromise commitment) {
        repository.changeDatePromise(commitment);
    }
}
