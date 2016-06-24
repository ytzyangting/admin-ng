package org.strela.admin.user.rest;

import org.springframework.stereotype.Component;
import org.strela.admin.user.User;
import org.strela.core.rest.ResourceConverter;

@Component
public class UserConverter implements ResourceConverter<User, UserResource> {

	@Override
	public UserResource convert(User user) {
		UserResource res = new UserResource();
		res.setId(user.getId());
		res.setName(user.getName());
		res.setUsername(user.getUsername());
		res.setPassword(user.getPassword());
		res.setRole(user.getRole());
		res.setEnabled(user.isEnabled());
		
		return res;
	}
	
}
