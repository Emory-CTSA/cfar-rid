package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CFARDemographics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cfar-demographics")
public interface CFARDemographicsRepository extends CrudRepository<CFARDemographics, String> {
    // Spring Data REST will expose CRUD endpoints automatically
}

