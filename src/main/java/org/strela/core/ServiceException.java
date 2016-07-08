package org.strela.core;

public class ServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(String message, Throwable t) {
		super(message, t);
	}

}
