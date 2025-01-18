package edu.ctsa.emory.cfar_rid.config;

import edu.ctsa.emory.cfar_rid.entity.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // This line exposes the ID for the entity class
        config.exposeIdsFor(CFARDemographics.class, CFARReservoirMeasurements.class,
                CfarArvStartDate.class, CfarArtAll.class, CfarMedsWithDates.class,
                CfarLog10Vl.class, CfarLymphocytesAll.class, CfarAbsCd4Loinc.class,
                CfarCd4Loinc.class, CfarCd4Loinc.class, CfarCd8Loinc.class,
                CfarCd4toCd8Loinc.class, CfarHbv.class, CfarHcv.class,
                CfarCbcChemistry.class, CfarDiagnosis.class);
    }
}
