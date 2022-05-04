package com.teampp.domain.general.valueobjects;

import com.teampp.domain.general.exceptions.WrongInputSyntaxException;

import java.util.Objects;

public final class BasicID {
    private final int userId;

    public BasicID(int userId) {
        if (userId<=0) {
            throw new WrongInputSyntaxException("User ID muss positiv sein");
        }
        this.userId = userId;
    }

    public int toInt() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicID userID = (BasicID) o;
        return userId == userID.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
