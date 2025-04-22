package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarHbv;
import edu.ctsa.emory.cfar_rid.repository.CfarHbvRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing CFAR HBV records.
 */
@Service
@Slf4j
public class CfarHbvService {

    private final CfarHbvRepository repository;

    public CfarHbvService(CfarHbvRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all CFAR HBV records.
     */
    public List<CfarHbv> getAll() {
        log.info("Fetching all CFAR HBV records...");
        List<CfarHbv> result = repository.findAll();
        log.debug("Retrieved {} HBV records", result.size());
        return result;
    }

    /**
     * Retrieves an HBV record by study ID.
     */
    public Optional<CfarHbv> getById(String studyId) {
        log.info("Fetching CFAR HBV record with ID: {}", studyId);
        return repository.findById(studyId);
    }

    /**
     * Saves or updates an HBV record.
     */
    public CfarHbv save(CfarHbv cfarHbv) {
        log.info("Saving CFAR HBV record for study ID: {}", cfarHbv.getStudyId());
        CfarHbv saved = repository.save(cfarHbv);
        log.debug("HBV record saved successfully for study ID: {}", saved.getStudyId());
        return saved;
    }

    /**
     * Deletes an HBV record by ID.
     */
    public boolean deleteById(String studyId) {
        log.info("Attempting to delete CFAR HBV record with ID: {}", studyId);
        if (repository.existsById(studyId)) {
            repository.deleteById(studyId);
            log.debug("Record with ID {} deleted successfully", studyId);
            return true;
        }
        log.warn("Record with ID {} not found. Nothing deleted.", studyId);
        return false;
    }
}
