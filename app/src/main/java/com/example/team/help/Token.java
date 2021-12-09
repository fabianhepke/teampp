package com.example.team.help;


import java.util.UUID;

public class Token {
    private final UUID token;

    public Token() {
        this.token = UUID.randomUUID();
    }

    public Token(String token) {
        this.token = UUID.fromString(token);
    }

    public String getToken() {
        return token.toString();
    }
}
