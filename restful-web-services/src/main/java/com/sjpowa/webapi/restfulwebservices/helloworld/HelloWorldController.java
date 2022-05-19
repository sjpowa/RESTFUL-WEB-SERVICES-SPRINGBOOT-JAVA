package com.sjpowa.webapi.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// Controller
@RestController
public class HelloWorldController {
	
	// so we have created a bean and now we want to take the value from messageSource
	@Autowired
	private MessageSource messageSource;
	
	// Longest way to set a method and a path
	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	// Down here there is the short version to map the URI
	@GetMapping(path = "/ciao-mondo")
	public String ciaoMondo() {
		return "Ciao Mondo";
	}
	
	// Return a Bean
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean HelloWorldBean() {
		return new HelloWorldBean("this bean msg will be automatically converted into a JSON file");
	}
	
	// Return a Bean - ITA version
	@GetMapping(path = "/ciao-mondo-fagiolo")
	public CiaoMondoFagiolo CiaoMondoFagiolo() {
		return new CiaoMondoFagiolo("per vedere nella pagina il msg in formato JSON ");
	}
	
	// Path Variable
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldPath HelloWorldPath(@PathVariable String name) {
		return new HelloWorldPath(name);
	}
	
	// ============================================================================================
	// INTERNATINALIZATION MESSAGE - GOOD MORNING IN MULTIPLE LANGUAGES
	
	// Here we are creating a method to GET a Good Morning
	// based on the language selected.
	// Locale is something to be given as input to this particular service.
	// How do we get Locale as an input? -> We need to add it as a parameter of this method Locale locale.
	// But how the consumer can pass the locale? -> The consumer can do that through header attributes.
	// The Request header has to be defined by the user but we set required = false because we want the US as default so if the user
	// will set a NON-EXISTING language the message will be shown in English 
//	@GetMapping(path = "/hello-world-internationalized")
//	public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", defaultValue = "us", required = false) Locale locale) {
//		return messageSource.getMessage("good.morning.message", null, locale);
//	}
	
	// There is another way, cleaner to have the same result, but in a better way
	// because I don't need to pass Locale as Parameter, so I don't need to set the Accept-Language that
	// as Default will be the US.
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message", null, // in null Object[] we can use this parameter to customize the message
				LocaleContextHolder.getLocale());
	}
	
	// ============================================================================================

}