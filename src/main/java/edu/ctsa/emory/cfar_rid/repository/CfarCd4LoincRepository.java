package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarCd4Loinc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cfar-cd4-loinc")
public interface CfarCd4LoincRepository extends CrudRepository<CfarCd4Loinc, String> {
}
