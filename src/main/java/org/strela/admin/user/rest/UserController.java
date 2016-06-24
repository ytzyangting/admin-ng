package org.strela.admin.user.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.strela.admin.user.User;
import org.strela.admin.user.UserRepository;
import org.strela.core.rest.ResourceNotFoundException;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserRepository userRepository;
	
	private UserConverter userConverter;
	
	public UserController(UserRepository userRepository, UserConverter userConverter) {
		this.userRepository = userRepository;
		this.userConverter = userConverter;
	}
	
	@GetMapping
	public List<UserResource> getUsers() {
		return userConverter.convertList(userRepository.findAll());
	}
	
	@GetMapping("/{userId}")
	public UserResource getUser(@PathVariable int userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new ResourceNotFoundException("User not found.");
		}
		
		return userConverter.convert(user);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResource createUser(@Validated @RequestBody UserResource res) {
		User user = new User();
		user.setName(res.getName());
		user.setUsername(res.getUsername());
		user.setPassword(res.getPassword());
		user.setRole(res.getRole());
		user.setEnabled(res.isEnabled());
		
		user = userRepository.save(user);
		
		return userConverter.convert(user);
	}
	
	@PutMapping("/{userId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public UserResource updateUser(@PathVariable int userId, @Validated @RequestBody UserResource res) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new ResourceNotFoundException("User not found.");
		}
		
		user.setName(res.getName());
		user.setRole(res.getRole());
		user.setEnabled(res.isEnabled());
		
		user = userRepository.save(user);
		
		return userConverter.convert(user);
	}
	
}
