package com.sps.friends.exceptions;

public class InvalidEmailException extends ApiException {
    public InvalidEmailException(String message) {
        super(message);
    }

    public InvalidEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public static void throwInvalidEmailException(String message) throws InvalidEmailException{
        throw new InvalidEmailException(message);
    }
}
