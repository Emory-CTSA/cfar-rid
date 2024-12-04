package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CFARReservoirMeasurements;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cfar-reservoir-measurements")
public interface ReservoirMeasurementRepository extends CrudRepository<CFARReservoirMeasurements, String> {
}
