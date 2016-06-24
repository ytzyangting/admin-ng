package org.strela.admin.user.rest;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.strela.core.rest.ResourceValidator;

@Component
public class UserValidator extends ResourceValidator<UserResource> {

	@Override
	protected void validateResource(UserResource resource, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "notEmpty", "Please enter value.");
	}

}
