package org.gov.qld.maintenance.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityCongfigure {
    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.roles}")
    private String adminRoles;

    @Value("${user.username}")
    private String userUsername;

    @Value("${user.password}")
    private String userPassword;

    @Value("${user.roles}")
    private String userRoles;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
        	.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/requests/admin/**").hasRole("ADMIN")
                .requestMatchers("/requests/user/**").hasAnyRole("USER")
                .requestMatchers("/requests/test/**").permitAll() // Allow access without authentication
                .anyRequest().authenticated() // Require authentication for all other requests
            )
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
            .build();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername(adminUsername)
            .password("{noop}" + adminPassword)
            .roles(adminRoles)
            .build());
        manager.createUser(User.withUsername(userUsername)
            .password("{noop}" + userPassword)
            .roles(userRoles)
            .build());
        return manager;
    }
}