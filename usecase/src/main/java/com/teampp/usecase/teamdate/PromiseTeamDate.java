package com.teampp.usecase.teamdate;

import com.teampp.domain.datepromise.DatePromise;
import com.teampp.domain.datepromise.DatePromiseRepository;

public class PromiseTeamDate {

    private final DatePromiseRepository repository;

    public PromiseTeamDate(DatePromiseRepository repository) {
        this.repository = repository;
    }

    public void promiseTeamDate(int dateID, int userID, boolean promise) {
        if (doesUserPromised(dateID, userID)) {
            changeCommitment(dateID, userID, promise);
            return;
        }
        DatePromise userPromise = new DatePromise(dateID, userID, promise);
        repository.addDatePromise(userPromise.getDateID(), userPromise.getUserID(), userPromise.isPromise());
    }

    private boolean doesUserPromised(int dateID, int userID) {
        return repository.doesUserPromisedDate(dateID, userID);
    }

    private void changeCommitment(int dateID, int userID, boolean promise) {
        DatePromise userPromise = new DatePromise(dateID, userID, promise);
        repository.changeDatePromise(userPromise.getDateID(), userPromise.getUserID(), userPromise.isPromise());
    }
}
