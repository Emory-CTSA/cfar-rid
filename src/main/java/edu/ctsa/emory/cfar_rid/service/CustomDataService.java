package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.dto.CohortCd4DTO;
import edu.ctsa.emory.cfar_rid.repository.CustomDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for custom data operations related to cohort CD4 reporting.
 */
@Service
@Slf4j
public class CustomDataService {

    private final CustomDataRepository repository;

    public CustomDataService(CustomDataRepository repository) {
        this.repository = repository;
    }

    /**
     * Fetches and maps raw CD4 data into a list of CohortCd4DTO objects.
     *
     * @return list of cohort CD4 data
     */
    public List<CohortCd4DTO> getCohortSiteCd4Data() {
        log.info("Fetching raw CD4 data from the database...");

        List<Object[]> rawData = repository.fetchCohortSiteCd4Raw();
        List<CohortCd4DTO> result = new ArrayList<>();

        for (Object[] row : rawData) {
            String cohort = row[0] != null ? row[0].toString() : null;
            String site = row[1] != null ? row[1].toString() : null;
            String cd4Count = row[2] != null ? row[2].toString() : null;

            result.add(new CohortCd4DTO(cohort, site, cd4Count));
        }

        log.debug("CD4 data mapped successfully. Total records: {}", result.size());
        return result;
    }
}
