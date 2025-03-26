package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CFARDemographics;
import edu.ctsa.emory.cfar_rid.repository.CFARDemographicsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing CFAR Demographic records.
 */
@Service
@Slf4j
public class CFARDemographicsService {

    private final CFARDemographicsRepository repository;

    public CFARDemographicsService(CFARDemographicsRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all CFAR demographic records from the database.
     * @return list of demographics
     */
    public List<CFARDemographics> getAll() {
        log.info("Fetching all CFAR demographic records...");
        List<CFARDemographics> result = repository.findAll();
        log.debug("Retrieved {} demographic records", result.size());
        return result;
    }

    /**
     * Retrieves a specific demographic record by study ID.
     * @param studyId the study ID
     * @return optional demographic record
     */
    public Optional<CFARDemographics> getById(String studyId) {
        log.info("Fetching CFAR demographic record with ID: {}", studyId);
        return repository.findById(studyId);
    }

    /**
     * Saves or updates a demographic record.
     * @param demographics the entity to save
     * @return the persisted entity
     */
    public CFARDemographics save(CFARDemographics demographics) {
        log.info("Saving CFAR demographic record for study ID: {}", demographics.getStudyId());
        CFARDemographics saved = repository.save(demographics);
        log.debug("Demographic record saved successfully for study ID: {}", saved.getStudyId());
        return saved;
    }

    /**
     * Deletes a demographic record by its study ID.
     * @param studyId the study ID
     * @return true if deleted, false otherwise
     */
    public boolean deleteById(String studyId) {
        log.info("Attempting to delete CFAR demographic record with ID: {}", studyId);
        if (repository.existsById(studyId)) {
            repository.deleteById(studyId);
            log.debug("Record with ID {} deleted successfully", studyId);
            return true;
        }
        log.warn("Record with ID {} not found. Nothing deleted.", studyId);
        return false;
    }
}
