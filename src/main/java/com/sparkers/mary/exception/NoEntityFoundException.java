package com.sparkers.mary.exception;

public class NoEntityFoundException extends RuntimeException {

    public NoEntityFoundException(String entityName, long id) {
        super(String.format("%s with id %s not found.", entityName, id));
    }
}
