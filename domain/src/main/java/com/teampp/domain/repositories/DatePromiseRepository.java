package com.teampp.domain.repositories;

import com.teampp.domain.entities.DatePromise;

public interface DatePromiseRepository {
    void addDatePromise(DatePromise datePromise);

    void changeDatePromise(DatePromise datePromise);

    boolean doesUserPromisedDate(int dateID, int userID);
}
