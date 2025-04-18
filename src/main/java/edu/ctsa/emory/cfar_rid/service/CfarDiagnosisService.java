package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarDiagnosis;
import edu.ctsa.emory.cfar_rid.repository.CfarDiagnosisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing CfarDiagnosis records.
 */
@Service
@Slf4j
public class CfarDiagnosisService {

    private final CfarDiagnosisRepository repository;

    public CfarDiagnosisService(CfarDiagnosisRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all diagnosis records.
     */
    public List<CfarDiagnosis> getAll() {
        log.info("Fetching all diagnosis records...");
        return repository.findAll();
    }

    /**
     * Retrieves a diagnosis record by Study ID.
     */
    public Optional<CfarDiagnosis> getById(String studyId) {
        log.info("Fetching diagnosis record by ID: {}", studyId);
        return repository.findById(studyId);
    }

    /**
     * Saves or updates a diagnosis record.
     */
    public CfarDiagnosis save(CfarDiagnosis entity) {
        log.info("Saving diagnosis record with ID: {}", entity.getStudyId());
        return repository.save(entity);
    }

    /**
     * Deletes a diagnosis record by Study ID.
     */
    public boolean deleteById(String studyId) {
        log.info("Deleting diagnosis record with ID: {}", studyId);
        if (repository.existsById(studyId)) {
            repository.deleteById(studyId);
            return true;
        }
        log.warn("Diagnosis record not found for ID: {}", studyId);
        return false;
    }
}
