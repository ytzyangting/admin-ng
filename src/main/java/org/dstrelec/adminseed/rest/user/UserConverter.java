package org.dstrelec.adminseed.rest.user;

import org.dstrelec.adminseed.user.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserResource> {

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
