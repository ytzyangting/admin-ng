package org.strela.admin.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.strela.core.rest.CompositeResourceValidator;
import org.strela.core.rest.ResourceValidator;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private CompositeResourceValidator validator;
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/ui/**").setViewName("index");
		registry.addViewController("/login").setViewName("login");
	}
	
	@Override
	public Validator getValidator() {
		return validator;
	}
	
	@Bean
	public CompositeResourceValidator compositeResourceValidator(Set<ResourceValidator<?>> validators) {
		return new CompositeResourceValidator(validators);
	}
	
}
