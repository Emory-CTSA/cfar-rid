package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarAbsCd8Loinc;
import edu.ctsa.emory.cfar_rid.entity.CfarHcv;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cfar-hcv")
public interface CfarHcvRepository extends CrudRepository<CfarHcv, String> {
}
