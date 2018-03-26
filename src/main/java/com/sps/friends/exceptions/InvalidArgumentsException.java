package com.sps.friends.exceptions;

public class InvalidArgumentsException extends ApiException {
    public InvalidArgumentsException(String message) {
        super(message);
    }

    public InvalidArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }
}
