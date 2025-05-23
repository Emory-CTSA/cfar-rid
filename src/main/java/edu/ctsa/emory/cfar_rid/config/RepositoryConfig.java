package edu.ctsa.emory.cfar_rid.config;

import edu.ctsa.emory.cfar_rid.entity.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * Repository configuration to expose entity IDs and manage CORS settings.
 */
@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

    /**
     * Configure repository REST settings including entity ID exposure and CORS.
     *
     * @param config RepositoryRestConfiguration to apply settings to
     * @param cors   CorsRegistry to define CORS rules
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // Expose IDs for JPA entities for REST access (required for frontend usage)
        config.exposeIdsFor(
                CFARDemographics.class,
                CFARReservoirMeasurements.class,
                CfarArvStartDate.class,
                CfarArtAll.class,
                CfarMedsWithDates.class,
                CfarLog10Vl.class,
                CfarLymphocytesAll.class,
                CfarAbsCd4Loinc.class,
                CfarCd4Loinc.class,
                CfarCd8Loinc.class,
                CfarCd4toCd8Loinc.class,
                CfarHbv.class,
                CfarHcv.class,
                CfarCbcChemistry.class,
                CfarDiagnosis.class
        );
    }
}
