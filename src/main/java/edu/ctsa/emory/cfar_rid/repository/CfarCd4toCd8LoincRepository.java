package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarCd4toCd8Loinc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cfar-cd4tocd8-loinc")
public interface CfarCd4toCd8LoincRepository extends CrudRepository<CfarCd4toCd8Loinc, String> {
}
