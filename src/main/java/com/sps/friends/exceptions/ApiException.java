package com.sps.friends.exceptions;

public class ApiException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2689043508291746621L;

	public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
