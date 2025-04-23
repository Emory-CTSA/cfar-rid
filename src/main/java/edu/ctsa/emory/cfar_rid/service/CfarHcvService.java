package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarHcv;
import edu.ctsa.emory.cfar_rid.repository.CfarHcvRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing CFAR Hepatitis C Virus (HCV) records.
 * Provides methods for CRUD operations and business logic related to HCV data.
 */
@Service
@Slf4j
public class CfarHcvService {

    private final CfarHcvRepository repository;

    public CfarHcvService(CfarHcvRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all HCV records from the database.
     *
     * @return List of {@link CfarHcv} entities
     */
    public List<CfarHcv> getAll() {
        log.info("Fetching all CFAR HCV records...");
        List<CfarHcv> result = repository.findAll();
        log.debug("Retrieved {} HCV records", result.size());
        return result;
    }

    /**
     * Retrieves an HCV record by its study ID.
     *
     * @param studyId the ID of the record to retrieve
     * @return Optional containing the found {@link CfarHcv} or empty if not found
     */
    public Optional<CfarHcv> getById(String studyId) {
        log.info("Fetching CFAR HCV record with ID: {}", studyId);
        return repository.findById(studyId);
    }

    /**
     * Creates or updates an HCV record in the database.
     *
     * @param cfarHcv the {@link CfarHcv} entity to save
     * @return the saved {@link CfarHcv} entity
     */
    public CfarHcv save(CfarHcv cfarHcv) {
        log.info("Saving CFAR HCV record for study ID: {}", cfarHcv.getStudyId());
        CfarHcv saved = repository.save(cfarHcv);
        log.debug("HCV record saved successfully for study ID: {}", saved.getStudyId());
        return saved;
    }

    /**
     * Deletes an HCV record by its study ID.
     *
     * @param studyId the ID of the record to delete
     * @return true if the record was deleted, false if it didn't exist
     */
    public boolean deleteById(String studyId) {
        log.info("Attempting to delete CFAR HCV record with ID: {}", studyId);
        if (repository.existsById(studyId)) {
            repository.deleteById(studyId);
            log.debug("Record with ID {} deleted successfully", studyId);
            return true;
        }
        log.warn("Record with ID {} not found. Nothing deleted.", studyId);
        return false;
    }
}
