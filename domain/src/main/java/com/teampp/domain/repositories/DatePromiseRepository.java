package com.teampp.domain.repositories;

public interface DatePromiseRepository {
    void addDatePromise(int dateID, int userID,  boolean promised);

    void changeDatePromise(int dateID, int userID,  boolean promised);

    boolean doesUserPromisedDate(int dateID, int userID);

    boolean isDatePromisePositive(int dateID, int userID);
}
