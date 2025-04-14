package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarCd8Loinc;
import edu.ctsa.emory.cfar_rid.repository.CfarCd8LoincRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service for managing CD8 LOINC records.
 */
@Service
@Slf4j
public class CfarCd8LoincService {

    private final CfarCd8LoincRepository repository;

    public CfarCd8LoincService(CfarCd8LoincRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all CD8 LOINC records.
     */
    public List<CfarCd8Loinc> getAll() {
        log.info("Fetching all CD8 LOINC records...");
        return repository.findAll();
    }

    /**
     * Retrieves a CD8 LOINC record by study ID.
     */
    public Optional<CfarCd8Loinc> getById(String studyId) {
        log.info("Fetching CD8 LOINC record by ID: {}", studyId);
        return repository.findById(studyId);
    }

    /**
     * Saves or updates a CD8 LOINC record.
     */
    public CfarCd8Loinc save(CfarCd8Loinc entity) {
        log.info("Saving CD8 LOINC record with ID: {}", entity.getStudyId());
        return repository.save(entity);
    }

    /**
     * Deletes a CD8 LOINC record by study ID.
     */
    public boolean deleteById(String studyId) {
        log.info("Deleting CD8 LOINC record with ID: {}", studyId);
        if (repository.existsById(studyId)) {
            repository.deleteById(studyId);
            return true;
        }
        log.warn("CD8 LOINC record not found for ID: {}", studyId);
        return false;
    }
}
