package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarDiagnosis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cfar-diagnosis")
public interface CfarDiagnosisRepository extends CrudRepository<CfarDiagnosis, String> {
}
