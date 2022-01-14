package com.example.team.components;

import com.example.team.database.PhpConnection;

public class TeamCode {
    final int code;

    public TeamCode(int code) {
        if (String.valueOf(code).length() != 6) {
            throw new IllegalArgumentException("Code must have 6 digits!");
        }
        this.code = code;
    }

    public TeamCode() {
        PhpConnection con = new PhpConnection();
        int tmp = con.getMaxTeamID() + 1;
        if (String.valueOf(tmp).length() != 6) {
            throw new IllegalArgumentException("Code must have 6 digits!");
        }
        this.code = tmp;
    }

    public int getCode() {
        return code;
    }

}
