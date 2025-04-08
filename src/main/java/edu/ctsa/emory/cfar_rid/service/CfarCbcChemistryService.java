package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarCbcChemistry;
import edu.ctsa.emory.cfar_rid.repository.CfarCbcChemistryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service for managing CBC Chemistry records.
 */
@Service
@Slf4j
public class CfarCbcChemistryService {

    private final CfarCbcChemistryRepository repository;

    public CfarCbcChemistryService(CfarCbcChemistryRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all CBC chemistry records.
     */
    public List<CfarCbcChemistry> getAll() {
        log.info("Fetching all CBC chemistry records...");
        return repository.findAll();
    }

    /**
     * Retrieves a CBC chemistry record by study ID.
     */
    public Optional<CfarCbcChemistry> getById(String studyId) {
        log.info("Fetching CBC chemistry record by ID: {}", studyId);
        return repository.findById(studyId);
    }

    /**
     * Saves or updates a CBC chemistry record.
     */
    public CfarCbcChemistry save(CfarCbcChemistry entity) {
        log.info("Saving CBC chemistry record with ID: {}", entity.getStudyId());
        return repository.save(entity);
    }

    /**
     * Deletes a CBC chemistry record by study ID.
     */
    public boolean deleteById(String studyId) {
        log.info("Deleting CBC chemistry record with ID: {}", studyId);
        if (repository.existsById(studyId)) {
            repository.deleteById(studyId);
            return true;
        }
        log.warn("CBC chemistry record not found for ID: {}", studyId);
        return false;
    }
}
