package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarLog10Vl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cfar-log10-vl")
public interface CfarLog10VlRepository extends CrudRepository<CfarLog10Vl, String> {
}
