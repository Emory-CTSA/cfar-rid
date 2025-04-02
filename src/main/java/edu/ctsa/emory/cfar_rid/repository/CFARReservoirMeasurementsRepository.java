package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CFARReservoirMeasurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing CFAR Reservoir Measurements.
 */
@Repository
public interface CFARReservoirMeasurementsRepository extends JpaRepository<CFARReservoirMeasurements, String> {
}
