package com.teampp.domain;

import com.teampp.domain.user.Token;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TokenTest {

    @Test
    public void tokenTest() {
        Token token = new Token();
        Token token1 = new Token("123e4567-e89b-12d3-a456-556642440000");
        assertTrue(token1.equals(new Token("123e4567-e89b-12d3-a456-556642440000")));
        assertFalse(token.equals(token1));
    }
}
