package com.teampp.domain.datepromise;

public class DatePromise {
    private int dateID, userID;
    private boolean promise;

    public DatePromise(int dateID, int userID, boolean promise) {
        this.dateID = dateID;
        this.userID = userID;
        this.promise = promise;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getDateID() {
        return dateID;
    }

    public void setDateID(int dateID) {
        this.dateID = dateID;
    }

    public boolean isPromise() {
        return promise;
    }

    public void setPromise(boolean promise) {
        this.promise = promise;
    }
}
