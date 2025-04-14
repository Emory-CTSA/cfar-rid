package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarCd4toCd8Loinc;
import edu.ctsa.emory.cfar_rid.repository.CfarCd4toCd8LoincRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service for managing CD4:CD8 LOINC records.
 */
@Service
@Slf4j
public class CfarCd4toCd8LoincService {

    private final CfarCd4toCd8LoincRepository repository;

    public CfarCd4toCd8LoincService(CfarCd4toCd8LoincRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all CD4:CD8 LOINC records.
     */
    public List<CfarCd4toCd8Loinc> getAll() {
        log.info("Fetching all CD4:CD8 LOINC records...");
        return repository.findAll();
    }

    /**
     * Retrieves a CD4:CD8 LOINC record by study ID.
     */
    public Optional<CfarCd4toCd8Loinc> getById(String studyId) {
        log.info("Fetching CD4:CD8 LOINC record by ID: {}", studyId);
        return repository.findById(studyId);
    }

    /**
     * Saves or updates a CD4:CD8 LOINC record.
     */
    public CfarCd4toCd8Loinc save(CfarCd4toCd8Loinc entity) {
        log.info("Saving CD4:CD8 LOINC record with ID: {}", entity.getStudyId());
        return repository.save(entity);
    }

    /**
     * Deletes a CD4:CD8 LOINC record by study ID.
     */
    public boolean deleteById(String studyId) {
        log.info("Deleting CD4:CD8 LOINC record with ID: {}", studyId);
        if (repository.existsById(studyId)) {
            repository.deleteById(studyId);
            return true;
        }
        log.warn("CD4:CD8 LOINC record not found for ID: {}", studyId);
        return false;
    }
}
