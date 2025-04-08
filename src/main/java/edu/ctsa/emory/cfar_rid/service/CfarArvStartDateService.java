package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarArvStartDate;
import edu.ctsa.emory.cfar_rid.repository.CfarArvStartDateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing ARV start date records.
 * Handles retrieval, persistence, and deletion of {@link CfarArvStartDate} data.
 */
@Service
@Slf4j
public class CfarArvStartDateService {

    private final CfarArvStartDateRepository repository;

    public CfarArvStartDateService(CfarArvStartDateRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all ARV start date records from the database.
     *
     * @return list of {@link CfarArvStartDate} entries
     */
    public List<CfarArvStartDate> getAll() {
        log.info("Fetching all ARV start date records...");
        return repository.findAll();
    }

    /**
     * Retrieves an ARV start date record by its study ID.
     *
     * @param studyId the study ID
     * @return optional containing the record if found
     */
    public Optional<CfarArvStartDate> getById(String studyId) {
        log.info("Fetching ARV start date record by ID: {}", studyId);
        return repository.findById(studyId);
    }

    /**
     * Saves or updates an ARV start date record.
     *
     * @param entity the {@link CfarArvStartDate} to save
     * @return the saved entity
     */
    public CfarArvStartDate save(CfarArvStartDate entity) {
        log.info("Saving ARV start date record with ID: {}", entity.getStudyId());
        return repository.save(entity);
    }

    /**
     * Deletes an ARV start date record by its study ID.
     *
     * @param studyId the study ID
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteById(String studyId) {
        log.info("Deleting ARV start date record with ID: {}", studyId);
        if (repository.existsById(studyId)) {
            repository.deleteById(studyId);
            return true;
        }
        log.warn("ARV start date record not found for ID: {}", studyId);
        return false;
    }
}
