package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarArtAll;
import edu.ctsa.emory.cfar_rid.repository.CfarArtAllRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing CFAR ART drug history records.
 */
@Service
@Slf4j
public class CfarArtAllService {

    private final CfarArtAllRepository repository;

    public CfarArtAllService(CfarArtAllRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all ART records.
     */
    public List<CfarArtAll> getAll() {
        log.info("Fetching all ART records...");
        return repository.findAll();
    }

    /**
     * Retrieves a specific ART record by study ID.
     */
    public Optional<CfarArtAll> getById(String studyId) {
        log.info("Fetching ART record with ID: {}", studyId);
        return repository.findById(studyId);
    }

    /**
     * Saves or updates an ART record.
     */
    public CfarArtAll save(CfarArtAll entity) {
        log.info("Saving ART record with ID: {}", entity.getStudyId());
        return repository.save(entity);
    }

    /**
     * Deletes an ART record by ID.
     */
    public boolean deleteById(String studyId) {
        log.info("Attempting to delete ART record with ID: {}", studyId);
        if (repository.existsById(studyId)) {
            repository.deleteById(studyId);
            return true;
        }
        log.warn("ART record not found for ID: {}", studyId);
        return false;
    }
}
