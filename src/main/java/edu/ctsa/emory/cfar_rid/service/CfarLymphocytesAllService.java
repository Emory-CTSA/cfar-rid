package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarLymphocytesAll;
import edu.ctsa.emory.cfar_rid.repository.CfarLymphocytesAllRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service class for managing CFAR Lymphocytes All records.
 */
@Service
@Slf4j
public class CfarLymphocytesAllService {

    private final CfarLymphocytesAllRepository repository;

    public CfarLymphocytesAllService(CfarLymphocytesAllRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieve all lymphocyte records.
     */
    public List<CfarLymphocytesAll> getAll() {
        log.info("Fetching all lymphocyte records...");
        return repository.findAll();
    }

    /**
     * Retrieve a specific record by study ID.
     */
    public Optional<CfarLymphocytesAll> getById(String studyId) {
        log.info("Fetching lymphocyte record with ID: {}", studyId);
        return repository.findById(studyId);
    }

    /**
     * Create or update a record.
     */
    public CfarLymphocytesAll save(CfarLymphocytesAll record) {
        log.info("Saving lymphocyte record for study ID: {}", record.getStudyId());
        return repository.save(record);
    }

    /**
     * Delete a record by study ID.
     */
    public boolean deleteById(String studyId) {
        log.info("Attempting to delete lymphocyte record with ID: {}", studyId);
        if (repository.existsById(studyId)) {
            repository.deleteById(studyId);
            return true;
        }
        log.warn("Record not found with ID: {}", studyId);
        return false;
    }
}
