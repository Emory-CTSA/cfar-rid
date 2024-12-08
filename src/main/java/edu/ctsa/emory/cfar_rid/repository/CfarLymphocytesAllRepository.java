package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarLymphocytesAll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cfar-lymphocytes-all")
public interface CfarLymphocytesAllRepository extends CrudRepository<CfarLymphocytesAll, Long> {
}
