package org.dstrelec.adminseed.rest.user;

import java.util.List;
import java.util.stream.Collectors;

import org.dstrelec.adminseed.user.User;
import org.dstrelec.adminseed.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

	private UserRepository userRepository;
	
	private UserConverter userConverter;
	
	@Autowired
	public UserRestController(UserRepository userRepository, UserConverter userConverter) {
		this.userRepository = userRepository;
		this.userConverter = userConverter;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<UserResource> getUsers() {
		return userRepository.findAll().stream()
				.map(user -> userConverter.convert(user))
				.collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public UserResource getUser(@PathVariable int userId) {
		User user = userRepository.findOne(userId);
		if (user != null) {
			return userConverter.convert(user);
		}
		
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public UserResource createUser(@RequestBody UserResource res) {
		User user = new User();
		user.setName(res.getName());
		user.setUsername(res.getUsername());
		user.setPassword(res.getPassword());
		user.setRole(res.getRole());
		user.setEnabled(res.isEnabled());
		
		user = userRepository.save(user);
		
		return userConverter.convert(user);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public UserResource updateUser(@PathVariable int userId, @RequestBody UserResource res) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			//TODO throw not found exception
		}
		
		user.setName(res.getName());
		user.setRole(res.getRole());
		user.setEnabled(res.isEnabled());
		
		user = userRepository.save(user);
		
		return userConverter.convert(user);
	}
	
}
