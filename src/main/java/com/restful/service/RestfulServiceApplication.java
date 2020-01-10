package com.restful.service;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class RestfulServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulServiceApplication.class, args);
	}	
	
	/*@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
	    StrictHttpFirewall firewall = new StrictHttpFirewall();
	    firewall.setAllowUrlEncodedSlash(true);    
	    return firewall;
	}
*/
	
	@Bean
	public LocaleResolver localeResolver(){		
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
	return localeResolver;	
	}
	
	//can do this configuration in the application.properties file 
	/*@Bean 
	public ResourceBundleViewResolver resourceBundleViewResolver()
	{
		ResourceBundleViewResolver messageSource = new ResourceBundleViewResolver();
		messageSource.setBasename("messages");
		return messageSource;
	}*/
}
