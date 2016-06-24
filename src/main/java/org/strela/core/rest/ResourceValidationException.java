package org.strela.core.rest;

import org.springframework.validation.Errors;

public class ResourceValidationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private Errors errors;
	
	public ResourceValidationException(String message, Errors errors) {
		super(message);
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}
	
}
