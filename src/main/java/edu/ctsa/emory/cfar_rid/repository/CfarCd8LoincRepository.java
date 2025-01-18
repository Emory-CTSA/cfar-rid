package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarCd8Loinc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cfar-cd8-loinc")
public interface CfarCd8LoincRepository extends CrudRepository<CfarCd8Loinc, String> {
}
