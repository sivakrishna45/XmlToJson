package com.conversions.xmlconverter.exceptions;

public class NoFileIdentifierException extends RuntimeException {
    private String message;

    public NoFileIdentifierException() {
    }

    public NoFileIdentifierException(String message) {
        super(message);
        this.message = message;
    }

}
