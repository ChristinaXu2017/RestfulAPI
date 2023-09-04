package org.qcmg.hairpin.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class BasicAuthenticationSecurityConfiguration1 {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {  
		

       return 
               http
               .authorizeHttpRequests(
                   auth -> auth 
                //   .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                   .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
                   .anyRequest().authenticated()
                )
               .httpBasic(Customizer.withDefaults())
               .sessionManagement(
                   session -> session.sessionCreationPolicy
                   (SessionCreationPolicy.STATELESS))
               .csrf().disable()
               .build();
	}
}
/*
This method cannot decide whether these patterns are Spring MVC patterns or not. 
If this endpoint is a Spring MVC endpoint, please use requestMatchers(MvcRequestMatcher); 
otherwise, please use requestMatchers(AntPathRequestMatcher).
at org.springframework.util.Assert.isTrue(Assert.java:122) ~[spring-core-6.0.11.jar:6.0.11]


 requestMatchers(HttpMethod method)

Caused by: java.lang.IllegalArgumentException: 
This method cannot decide whether these patterns are Spring MVC patterns or not. 
If this endpoint is a Spring MVC endpoint, please use requestMatchers(MvcRequestMatcher);
 otherwise, please use requestMatchers(AntPathRequestMatcher).



*/