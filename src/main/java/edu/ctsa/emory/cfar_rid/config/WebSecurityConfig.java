package edu.ctsa.emory.cfar_rid.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security configuration using WebSecurityConfigurerAdapter (pre-Spring 5.7).
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Configure HTTP security rules.
     *
     * @param http HttpSecurity object
     * @throws Exception in case of configuration error
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors() // Enable CORS
                .and()
                .csrf().disable() // ⚠️ Disable CSRF only if you're not using session-based login
                .authorizeRequests()
                .antMatchers("/api/**").permitAll() // Public endpoints
                .anyRequest().authenticated()       // Secure all other endpoints
                .and()
                .httpBasic(); // ⚠️ Replace with JWT/OAuth2 in production if possible
    }
}
