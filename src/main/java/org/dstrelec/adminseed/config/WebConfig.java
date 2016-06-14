package org.dstrelec.adminseed.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/ui/**").setViewName("index");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/view-1").setViewName("view1");
		registry.addViewController("/view-2").setViewName("view2");
	}
	
}
