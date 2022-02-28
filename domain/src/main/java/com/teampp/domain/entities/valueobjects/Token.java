package com.teampp.domain.entities.valueobjects;

import com.teampp.domain.entities.exceptions.WrongInputSyntaxException;

import java.util.Objects;
import java.util.UUID;

public final class Token {
    private final UUID token;

    public Token() {
        this.token = UUID.randomUUID();
    }

    public Token(String token){
        this.token = UUID.fromString(token);
    }

    @Override
    public String toString() {
        return token.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return Objects.equals(token, token1.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
