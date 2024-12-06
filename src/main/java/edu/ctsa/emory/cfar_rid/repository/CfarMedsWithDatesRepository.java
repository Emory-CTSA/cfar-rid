package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarMedsWithDates;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cfar-meds-with-dates")
public interface CfarMedsWithDatesRepository extends CrudRepository<CfarMedsWithDates, String> {
}
