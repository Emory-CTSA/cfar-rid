package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarLymphocytesAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for CFAR Lymphocytes All data.
 */
@Repository
public interface CfarLymphocytesAllRepository extends JpaRepository<CfarLymphocytesAll, String> {
}
