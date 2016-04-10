package org.myapp.exception;

public class CallParentException extends RuntimeException {

	private static final long serialVersionUID = 1515878805238708397L;

	public CallParentException(String message) {
		super(message);
	}

}
