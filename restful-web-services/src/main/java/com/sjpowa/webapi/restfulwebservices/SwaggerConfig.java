package com.sjpowa.webapi.restfulwebservices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Annotating a class with the
// @Configuration annotation indicates that the class will be used by JavaConfig as a source of bean definitions.
@Configuration // We want to say to Spring Boot that this is a Configuration File
@EnableSwagger2 // Enable Swagger
public class SwaggerConfig {
	
	// URL
	// http://localhost:8080/v2/api-docs
	// localhost:8080/swagger-ui/

	
	// From ApiInfo we copy and paste DEFAULT_CONTACT and DEFAULT informations we want to customize
	
	public static final Contact DEFAULT_CONTACT
    = new Contact(
    "Pippo",
	"www.superpipp.com",
    "superpippa@gmail.com");
	public static final ApiInfo DEFAULT_API_INFO
    = new ApiInfo(
    "Titleeee of this WebAPI",
    "Descriptioooon of this WebAPI",
    "1.0",
    "urn:tos",
    DEFAULT_CONTACT,
    "Apache 2.0",
    "http://www.apache.org/licenses/LICENSE-2.0",
    new ArrayList<>());
	private static final Set<String> DEFAULT_JSON_AND_XML = 
			new HashSet<String>(Arrays.asList("application/json"));
//	new HashSet<String>(Arrays.asList("application/json", "application/xml"));
	// Actually we have commented the xml format dependency, so we add as produces only JSON format
	
	// REMEMBER: Here we declare the method api() a BEAN because it is a Docket type,
	// so this method returning back a Docket instance, Spring Boot to manage the instance
	// has to know that this down here is a BEAN!.
	// Spring Boot manages instances as Beans
	// If u don't put the annotation, nothing will work
	
	// Bean - Docket
	// Docket is just a Class with a lot of stuff that we can implement in to customize our Documentation
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_JSON_AND_XML)
				.consumes(DEFAULT_JSON_AND_XML);
		
		// with 'DocumentationType.SWAGGER_2' we have enabled this type of documentation we want to use
		// apiInfo() -> we use it to Customize our Documentation
		// With consumes and produces we are going to specify with which format the service works
	}
	
}
