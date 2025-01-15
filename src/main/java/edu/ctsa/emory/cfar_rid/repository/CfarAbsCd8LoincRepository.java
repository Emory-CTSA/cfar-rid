package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarAbsCd8Loinc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cfar-abs-cd8-loinc")
public interface CfarAbsCd8LoincRepository extends CrudRepository<CfarAbsCd8Loinc, String> {
}


