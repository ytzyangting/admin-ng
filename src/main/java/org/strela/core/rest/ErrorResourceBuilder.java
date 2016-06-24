package org.strela.core.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class ErrorResourceBuilder {
	
	private String message;
	
	private Map<String, String> fields;
	
	ErrorResourceBuilder(String message, Map<String, String> fields) {
		this.message = message;
		this.fields = fields;
	}
	
	public ErrorResourceBuilder message(String message) {
		this.message = message;
		return this;
	}
	
	public ErrorResourceBuilder field(String name, String message) {
		this.fields.put(name, message);
		return this;
	}
	
	public ErrorResource build() {
		ErrorResource res = new ErrorResource();
		res.setMessage(message);
		
		if (!CollectionUtils.isEmpty(fields)) {
			res.setFields(fields);
		}
		
		return res;
	}
	
	public static ErrorResourceBuilder fromMessage(String message) {
		return new ErrorResourceBuilder(message, new HashMap<>());
	}
	
	public static ErrorResourceBuilder fromFields(Map<String, String> fields) {
		return new ErrorResourceBuilder("Invalid resource", fields);
	}
	
	public static ErrorResourceBuilder fromErrors(Errors errors) {
		String message = null;
		
		ObjectError globalError = errors.getGlobalError();
		if (globalError != null) {
			message = globalError.getDefaultMessage();
		}
		
		if (StringUtils.isEmpty(message)) {
			message = "Invalid resource";
		}
		
		Map<String, String> fields = new HashMap<>();
		errors.getFieldErrors().forEach(f -> fields.put(f.getField(), f.getDefaultMessage()));
		
		return new ErrorResourceBuilder(message, fields);
	}

	public static ErrorResourceBuilder fromException(Exception e) {
		if (e instanceof MethodArgumentNotValidException) {
			return fromErrors(((MethodArgumentNotValidException) e).getBindingResult());
		} else if (e instanceof ResourceValidationException) {
			return fromErrors(((ResourceValidationException) e).getErrors());
		}
		
		return fromMessage(e.getMessage());
	}
	
}
