package org.strela.admin;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.strela.core.rest.ErrorResource;
import org.strela.core.rest.ErrorResourceBuilder;
import org.strela.core.rest.ResourceNotFoundException;
import org.strela.core.rest.ResourceValidationException;
import org.strela.core.rest.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdminRestControllerAdvice {

	@ExceptionHandler({MethodArgumentNotValidException.class, ResourceValidationException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResource handleNotValidException(Exception e) {
		return ErrorResourceBuilder.fromException(e).build();
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResource handleNotFoundException(ResourceNotFoundException e) {
		return ErrorResourceBuilder.fromException(e).build();
	}
	
}
