package org.strela.core.rest;

import java.util.Map;

public class ErrorResource {

	private String message;
	
	private Map<String, String> fields;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getFields() {
		return fields;
	}

	public void setFields(Map<String, String> fields) {
		this.fields = fields;
	}
	
}
