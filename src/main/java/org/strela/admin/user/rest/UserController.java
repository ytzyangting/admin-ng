package org.strela.admin.user.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.strela.admin.user.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService userService;
	
	private UserConverter userConverter;
	
	public UserController(UserService userService, UserConverter userConverter) {
		this.userService = userService;
		this.userConverter = userConverter;
	}
	
	@GetMapping
	public Page<UserResource> getUsers(Pageable pageable) {
		return userConverter.convertPage(userService.getUsers(pageable));
	}
	
	@GetMapping("/{userId}")
	public UserResource getUser(@PathVariable int userId) {
		return userConverter.convert(userService.getUser(userId));
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
		
		user = userService.createUser(user);
		
		return userConverter.convert(user);
	}
	
	@PutMapping("/{userId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public UserResource updateUser(@PathVariable int userId, @Validated @RequestBody UserResource res) {
		User user = userService.getUser(userId);
		user.setName(res.getName());
		user.setRole(res.getRole());
		user.setEnabled(res.isEnabled());
		
		user = userService.updateUser(user);
		
		return userConverter.convert(user);
	}
	
}
