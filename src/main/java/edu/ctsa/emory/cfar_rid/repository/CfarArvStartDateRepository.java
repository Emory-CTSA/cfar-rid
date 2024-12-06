package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarArvStartDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cfar-arv-start-date")
public interface CfarArvStartDateRepository extends CrudRepository<CfarArvStartDate, String> {
}
