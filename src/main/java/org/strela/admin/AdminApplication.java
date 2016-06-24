package org.strela.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.strela.admin.user.User;
import org.strela.admin.user.UserRepository;

@SpringBootApplication
public class AdminApplication {

	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initializr() {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				User sa = new User();
				sa.setName("SA");
				sa.setUsername("sa");
				sa.setPassword("admin");
				sa.setRole("SUPER_ADMINISTRATOR");
				sa.setEnabled(true);
				
				userRepository.save(sa);
				
				User johnSmith = new User();
				johnSmith.setName("John Smith");
				johnSmith.setUsername("john.smith");
				johnSmith.setPassword("jsmith12345");
				johnSmith.setRole("ADMINISTRATOR");
				johnSmith.setEnabled(false);
				
				userRepository.save(johnSmith);
			}
		};
	}
	
}
