package edu.ctsa.emory.cfar_rid.config;

import edu.ctsa.emory.cfar_rid.entity.CFARDemographics;
import edu.ctsa.emory.cfar_rid.entity.CFARReservoirMeasurements;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // This line exposes the ID for the CFARDemographics entity
        config.exposeIdsFor(CFARDemographics.class, CFARReservoirMeasurements.class);
    }
}
