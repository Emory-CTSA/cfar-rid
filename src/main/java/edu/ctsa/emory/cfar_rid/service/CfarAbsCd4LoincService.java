package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarAbsCd4Loinc;
import edu.ctsa.emory.cfar_rid.repository.CfarAbsCd4LoincRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for handling CFAR Absolute CD4 LOINC operations.
 */
@Service
@Slf4j
public class CfarAbsCd4LoincService {

    private final CfarAbsCd4LoincRepository repository;

    public CfarAbsCd4LoincService(CfarAbsCd4LoincRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all CD4 LOINC records from the database.
     *
     * @return list of all CD4 LOINC entries
     */
    public List<CfarAbsCd4Loinc> getAll() {
        log.info("Fetching all CD4 LOINC records...");
        List<CfarAbsCd4Loinc> result = repository.findAll();
        log.debug("Fetched {} records", result.size());
        return result;
    }

    /**
     * Retrieves a specific CD4 LOINC record by study ID.
     *
     * @param studyId the study ID
     * @return optional containing the record if found, or empty
     */
    public Optional<CfarAbsCd4Loinc> getById(String studyId) {
        log.info("Fetching record by ID: {}", studyId);
        return repository.findById(studyId);
    }

    /**
     * Saves or updates a CD4 LOINC record.
     *
     * @param entity the record to be saved
     * @return the persisted record
     */
    public CfarAbsCd4Loinc save(CfarAbsCd4Loinc entity) {
        log.info("Saving CD4 LOINC record: {}", entity.getStudyId());
        return repository.save(entity);
    }

    /**
     * Deletes a CD4 LOINC record by its study ID.
     *
     * @param studyId the study ID
     * @return true if deleted, false otherwise
     */
    public boolean deleteById(String studyId) {
        log.info("Deleting CD4 LOINC record by ID: {}", studyId);
        if (repository.existsById(studyId)) {
            repository.deleteById(studyId);
            log.debug("Deleted record with ID: {}", studyId);
            return true;
        }
        log.warn("Record not found for ID: {}", studyId);
        return false;
    }
}
