package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarDiagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CfarDiagnosis entity.
 */
@Repository
public interface CfarDiagnosisRepository extends JpaRepository<CfarDiagnosis, String> {
}
