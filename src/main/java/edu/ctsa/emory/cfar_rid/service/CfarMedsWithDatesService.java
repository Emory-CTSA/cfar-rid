package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarMedsWithDates;
import edu.ctsa.emory.cfar_rid.repository.CfarMedsWithDatesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service class for managing CFAR Medications with Dates data.
 * Provides methods for retrieving, saving, and deleting records.
 */
@Service
@Slf4j
public class CfarMedsWithDatesService {

    private final CfarMedsWithDatesRepository repository;

    public CfarMedsWithDatesService(CfarMedsWithDatesRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all medication records from the database.
     *
     * @return List of {@link CfarMedsWithDates} entities
     */
    public List<CfarMedsWithDates> getAll() {
        log.info("Fetching all medication records...");
        return repository.findAll();
    }

    /**
     * Retrieves a specific medication record by its study ID.
     *
     * @param studyId the study ID of the record
     * @return Optional containing the medication record if found, empty otherwise
     */
    public Optional<CfarMedsWithDates> getById(String studyId) {
        log.info("Fetching medication record with ID: {}", studyId);
        return repository.findById(studyId);
    }

    /**
     * Saves or updates a medication record.
     *
     * @param meds the {@link CfarMedsWithDates} entity to be saved
     * @return the saved {@link CfarMedsWithDates} entity
     */
    public CfarMedsWithDates save(CfarMedsWithDates meds) {
        log.info("Saving medication record for study ID: {}", meds.getStudyId());
        return repository.save(meds);
    }

    /**
     * Deletes a medication record by its study ID.
     *
     * @param studyId the study ID of the record to delete
     * @return true if the record was deleted, false if it did not exist
     */
    public boolean deleteById(String studyId) {
        log.info("Attempting to delete medication record with ID: {}", studyId);
        if (repository.existsById(studyId)) {
            repository.deleteById(studyId);
            log.debug("Medication record with ID {} deleted successfully", studyId);
            return true;
        }
        log.warn("Medication record not found for ID: {}", studyId);
        return false;
    }
}
