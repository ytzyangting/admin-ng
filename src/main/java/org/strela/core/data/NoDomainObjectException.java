package org.strela.core.data;

import org.strela.core.ServiceException;

public class NoDomainObjectException extends ServiceException {
	
	private static final long serialVersionUID = 1L;

	public NoDomainObjectException(String message) {
		super(message);
	}

}
