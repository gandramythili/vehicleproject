package com.vehicle.exception;

public class InvalidRequest extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidRequest() {
		super();
	}

	public InvalidRequest(String message) {
		super(message);

	}

}
