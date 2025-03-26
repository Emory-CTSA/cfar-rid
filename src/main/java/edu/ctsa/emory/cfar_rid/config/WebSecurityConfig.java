package edu.ctsa.emory.cfar_rid.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors() // If you're using CORS settings
                .and()
                .csrf().disable() // Disable CSRF protection for simplicity in non-production
                .authorizeRequests()
                .antMatchers("/api/**").permitAll() // Allow all accesses to API endpoints
                .anyRequest().authenticated() // All other requests need authentication
                .and()
                .httpBasic(); // Basic authentication, you might want to use a different or more secure method in production
    }
}

