package edu.ctsa.emory.cfar_rid.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration for global CORS settings.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Load allowed origin from application.properties/yaml
    @Value("${app.cors.allowedOrigin:http://localhost:3000}")
    private String allowedOrigin;

    /**
     * Configure Cross-Origin Resource Sharing (CORS) for all endpoints.
     *
     * @param registry CorsRegistry to define CORS mappings
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigin)  // Dynamically injected origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
