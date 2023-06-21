package com.travel.travtronics.exception;

public class NotFoundException extends Exception {

	public NotFoundException(String entityId, Class c) {
		super(String.format("The entity %s could not be found with the ID: %s", c.getSimpleName(), entityId));
	}

}
