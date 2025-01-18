package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarCbcChemistry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cfar-cbc-chemistry")
public interface CfarCbcChemistryRepository extends CrudRepository<CfarCbcChemistry, String> {
}
