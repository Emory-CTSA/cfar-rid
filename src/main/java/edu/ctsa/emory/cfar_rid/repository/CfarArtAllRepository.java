package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarArtAll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cfar-art-all")
public interface CfarArtAllRepository extends CrudRepository<CfarArtAll, String> {
}
