package com.sps.friends.exceptions;

public class InvalidArgumentsException extends ApiException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -287454948471644078L;

	public InvalidArgumentsException(String message) {
        super(message);
    }

    public InvalidArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }
}
