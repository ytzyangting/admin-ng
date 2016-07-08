package org.strela.admin;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.strela.core.ServiceException;
import org.strela.core.data.NoDomainObjectException;
import org.strela.core.rest.ErrorResource;
import org.strela.core.rest.ErrorResourceBuilder;
import org.strela.core.rest.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdminRestControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResource handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return ErrorResourceBuilder.fromException(e).build();
	}
	
	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResource handleServiceException(ServiceException e) {
		return ErrorResourceBuilder.fromException(e).build();
	}
	
	@ExceptionHandler(NoDomainObjectException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResource handleNoDomainObjectException(NoDomainObjectException e) {
		return ErrorResourceBuilder.fromException(e).build();
	}
	
}
