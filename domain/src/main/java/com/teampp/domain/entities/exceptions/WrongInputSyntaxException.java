package com.teampp.domain.entities.exceptions;

public class WrongInputSyntaxException extends RuntimeException{
    public WrongInputSyntaxException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public WrongInputSyntaxException(String errorMEssage) {
        super(errorMEssage);
    }
}
