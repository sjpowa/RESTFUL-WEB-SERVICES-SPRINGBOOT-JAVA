package com.sjpowa.webapi.restfulwebservices;

import java.lang.module.ResolutionException;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

	// This is the First Way to configure Internationalization
	// passing the parameter in the method in Controller Class
//	@Bean
//	public LocaleResolver localeResolver() {
//		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//		localeResolver.setDefaultLocale(Locale.US);
//		return localeResolver;
//	}
	
	// This is the second way where in the controller
	// as u can see I have done another method where
	// I don't need the Header Request anymore
	// so I don't pass a Parameter in the GET Method
	// and
	// as Default we have set Locale.US so if we do not declare in the header
	// the language we have Good Morning msg
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
	// We need something to read these message.properties
	// and customize them base on the input accepted header [Postman -> Header -> Accept-Language -> it]
	// How do we do that?
	// We have to add a new Bean
	@Bean
	public ResourceBundleMessageSource messageSource() {
			ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
			messageSource.setBasename("messages"); // messages because our properties are called messages.properties
			return messageSource;
	}
	
}
