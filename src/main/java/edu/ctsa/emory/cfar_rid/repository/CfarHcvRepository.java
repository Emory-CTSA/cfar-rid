package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarHcv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing CFAR HCV entity persistence.
 */
@Repository
public interface CfarHcvRepository extends JpaRepository<CfarHcv, String> {
}
