package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarLog10Vl;
import edu.ctsa.emory.cfar_rid.repository.CfarLog10VlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Log10 Viral Load (VL) data.
 */
@Service
@Slf4j
public class CfarLog10VlService {

    private final CfarLog10VlRepository repository;

    public CfarLog10VlService(CfarLog10VlRepository repository) {
        this.repository = repository;
    }

    /**
     * Fetch all VL records.
     */
    public List<CfarLog10Vl> getAll() {
        log.info("Fetching all Log10 VL records...");
        return repository.findAll();
    }

    /**
     * Fetch a VL record by study ID.
     */
    public Optional<CfarLog10Vl> getById(String studyId) {
        log.info("Fetching VL record by ID: {}", studyId);
        return repository.findById(studyId);
    }

    /**
     * Save or update a VL record.
     */
    public CfarLog10Vl save(CfarLog10Vl record) {
        log.info("Saving VL record for study ID: {}", record.getStudyId());
        return repository.save(record);
    }

    /**
     * Delete a VL record by study ID.
     */
    public boolean deleteById(String studyId) {
        log.info("Attempting to delete VL record for study ID: {}", studyId);
        if (repository.existsById(studyId)) {
            repository.deleteById(studyId);
            log.debug("Deleted VL record with ID: {}", studyId);
            return true;
        }
        log.warn("No VL record found for ID: {}", studyId);
        return false;
    }
}
