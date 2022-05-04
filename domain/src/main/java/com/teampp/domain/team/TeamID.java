package com.teampp.domain.team;

import com.teampp.domain.general.exceptions.WrongInputSyntaxException;

import java.util.Objects;

public final class TeamID {
    final int code;

    public TeamID(int code){
        if (String.valueOf(code).length() != 6) {
            throw new WrongInputSyntaxException("TeamID muss genau 6 Stellen haben!");
        }
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamID teamID = (TeamID) o;
        return code == teamID.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    public int toInt() {
        return code;
    }

}
