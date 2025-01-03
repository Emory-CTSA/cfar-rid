package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarAbsCd4Loinc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cfar-abs-cd4-loinc")
public interface CfarAbsCd4LoincRepository extends CrudRepository<CfarAbsCd4Loinc, String> {
}
