package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CFARReservoirMeasurements;
import edu.ctsa.emory.cfar_rid.repository.CFARReservoirMeasurementsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service class for managing CFAR Reservoir Measurements.
 */
@Service
@Slf4j
public class CFARReservoirMeasurementsService {

    private final CFARReservoirMeasurementsRepository repository;

    public CFARReservoirMeasurementsService(CFARReservoirMeasurementsRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieve all records.
     *
     * @return list of {@link CFARReservoirMeasurements}
     */
    public List<CFARReservoirMeasurements> getAll() {
        log.info("Fetching all reservoir measurement records...");
        return repository.findAll();
    }

    /**
     * Retrieve a record by person key.
     *
     * @param personKey the person key
     * @return Optional of {@link CFARReservoirMeasurements}
     */
    public Optional<CFARReservoirMeasurements> getById(String personKey) {
        log.info("Fetching record for personKey: {}", personKey);
        return repository.findById(personKey);
    }

    /**
     * Save or update a record.
     *
     * @param record the reservoir measurement to save
     * @return saved {@link CFARReservoirMeasurements}
     */
    public CFARReservoirMeasurements save(CFARReservoirMeasurements record) {
        log.info("Saving record for personKey: {}", record.getPersonKey());
        return repository.save(record);
    }

    /**
     * Delete a record by person key.
     *
     * @param personKey the key to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteById(String personKey) {
        log.info("Attempting to delete record with personKey: {}", personKey);
        if (repository.existsById(personKey)) {
            repository.deleteById(personKey);
            return true;
        }
        log.warn("Record not found for personKey: {}", personKey);
        return false;
    }
}
