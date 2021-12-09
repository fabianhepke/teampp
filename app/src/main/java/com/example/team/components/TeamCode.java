package com.example.team.components;

public class TeamCode {
    final int code;

    public TeamCode(int code) {
        if ((Math.log10(code) + 1) != 6) {
            throw new IllegalArgumentException("Code must have 6 digits!");
        }
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
