package com.example.rest.service.exception;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(Throwable e) {
		super(e);
	}

}
