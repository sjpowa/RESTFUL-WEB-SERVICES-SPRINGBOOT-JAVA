package com.sjpowa.webapi.restfulwebservices;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

// The @EnableWebSecurity annotation is crucial if we disable the default security configuration.
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// ========================================================================
	// With this way we have configured two users to access
	
	// we need to use the PasswordEncoder to set the passwords when using Spring Boot 2.
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	PasswordEncoder encoder = 
          PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	auth
          .inMemoryAuthentication()
          .withUser("user")
          .password(encoder.encode("password"))
          .roles("USER")
          .and()
          .withUser("admin")
          .password(encoder.encode("admin"))
          .roles("USER", "ADMIN");
    }

	 @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .csrf().disable()
          .authorizeRequests()
          .anyRequest()
          .authenticated()
          .and()
          .httpBasic();
    }
	 
	// =======================================================================================
	// With this method down here we give full access and permit all operations to be done
	 
	// with "/**" equals to say all paths
		// so adding it with .permitAll() we are allowing
		// to everything, reading a performing actions
		// on all paths
//		private static final String[] AUTH_WHITELIST = {
//				   "/v2/api-docs", "/swagger-ui/", "/swagger-resources", "/swagger-resources/**", "/**",
//				 };
	//	
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//		    http.csrf().disable().authorizeRequests()
//		            .antMatchers(AUTH_WHITELIST).permitAll()
//		            .antMatchers("/csrf").permitAll()
//		            .anyRequest().authenticated();
//	     }

}