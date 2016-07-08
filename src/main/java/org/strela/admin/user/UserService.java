package org.strela.admin.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.strela.core.data.NoDomainObjectException;

@Service
public class UserService {

	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User getUser(int userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new NoDomainObjectException("User " + userId + " not found.");
		}
		
		return user;
	}
	
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}
	
	public Page<User> getUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
}
