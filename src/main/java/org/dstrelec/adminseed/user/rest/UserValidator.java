package org.dstrelec.adminseed.user.rest;

import org.dstrelec.core.rest.ResourceValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class UserValidator extends ResourceValidator<UserResource> {

	@Override
	protected void validateResource(UserResource resource, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "notEmpty", "Please enter value.");
	}

}
