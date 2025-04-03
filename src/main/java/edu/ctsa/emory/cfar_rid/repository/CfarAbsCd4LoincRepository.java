package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarAbsCd4Loinc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for cfar abs cd4 loinc data.
 */
@Repository
public interface CfarAbsCd4LoincRepository extends JpaRepository<CfarAbsCd4Loinc, String> {
}
