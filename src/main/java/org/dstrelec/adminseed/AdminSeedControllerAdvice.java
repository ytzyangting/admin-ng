package org.dstrelec.adminseed;

import org.dstrelec.core.rest.ErrorResource;
import org.dstrelec.core.rest.ErrorResourceBuilder;
import org.dstrelec.core.rest.ResourceNotFoundException;
import org.dstrelec.core.rest.ResourceValidationException;
import org.dstrelec.core.rest.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestControllerAdvice
public class AdminSeedControllerAdvice {

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
