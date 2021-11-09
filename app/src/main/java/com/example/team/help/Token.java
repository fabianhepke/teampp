package com.example.team.help;

public class Token {
    private final String token;

    public Token(int length) {
        this.token = new RandomString(length).nextString();
    }

    public String getToken() {
        return token;
    }
}
