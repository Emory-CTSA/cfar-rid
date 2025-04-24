package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarLog10Vl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for CFAR Log10 VL data access.
 */
@Repository
public interface CfarLog10VlRepository extends JpaRepository<CfarLog10Vl, String> {
}
