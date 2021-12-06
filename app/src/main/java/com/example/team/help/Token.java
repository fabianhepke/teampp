package com.example.team.help;


import java.util.UUID;

public class Token {
    private final UUID token;

    public Token() {
        this.token = UUID.randomUUID();
    }

    public String toString() {
        return token.toString();
    }
}
