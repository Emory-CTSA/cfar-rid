package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarAbsCd8Loinc;
import edu.ctsa.emory.cfar_rid.repository.CfarAbsCd8LoincRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing CFAR Absolute CD8 LOINC records.
 */
@Service
@Slf4j
public class CfarAbsCd8LoincService {

    private final CfarAbsCd8LoincRepository repository;

    public CfarAbsCd8LoincService(CfarAbsCd8LoincRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all CD8 records.
     */
    public List<CfarAbsCd8Loinc> getAll() {
        log.info("Fetching all CD8 records...");
        return repository.findAll();
    }

    /**
     * Gets a CD8 record by study ID.
     */
    public Optional<CfarAbsCd8Loinc> getById(String studyId) {
        log.info("Fetching CD8 record by ID: {}", studyId);
        return repository.findById(studyId);
    }

    /**
     * Saves or updates a CD8 record.
     */
    public CfarAbsCd8Loinc save(CfarAbsCd8Loinc entity) {
        log.info("Saving CD8 record: {}", entity.getStudyId());
        return repository.save(entity);
    }

    /**
     * Deletes a CD8 record by ID.
     */
    public boolean deleteById(String studyId) {
        log.info("Deleting CD8 record by ID: {}", studyId);
        if (repository.existsById(studyId)) {
            repository.deleteById(studyId);
            return true;
        }
        return false;
    }
}
