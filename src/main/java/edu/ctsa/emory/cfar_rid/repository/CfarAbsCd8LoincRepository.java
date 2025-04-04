package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarAbsCd8Loinc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for cfar abs cd8 loinc data.
 */
@Repository
public interface CfarAbsCd8LoincRepository extends JpaRepository<CfarAbsCd8Loinc, String> {
}


