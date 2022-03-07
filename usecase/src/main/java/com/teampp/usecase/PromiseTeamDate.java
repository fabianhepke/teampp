package com.teampp.usecase;

import com.teampp.domain.entities.DatePromise;
import com.teampp.domain.repositories.DatePromiseRepository;

public class PromiseTeamDate {

    private final DatePromiseRepository repository;

    public PromiseTeamDate(DatePromiseRepository repository) {
        this.repository = repository;
    }

    public void promiseTeamDate(int userID, int dateID, boolean promise) {
        DatePromise userPromise = new DatePromise(dateID, userID, promise);
        repository.addDatePromise(userPromise);
    }

    public boolean doesUserCommited(int userID, int dateID) {
        return repository.doesUserPromisedDate(dateID, userID);
    }

    public void changeCommitment(int dateID, int userID, boolean promise) {
        DatePromise userPromise = new DatePromise(dateID, userID, promise);
        repository.changeDatePromise(userPromise);
    }
}
