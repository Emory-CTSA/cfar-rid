package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CFARDemographics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for cfar demographics data.
 */
@Repository
public interface CFARDemographicsRepository extends JpaRepository<CFARDemographics, String> {
    // Spring Data REST will expose CRUD endpoints automatically
}

