package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarMedsWithDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for CFAR Meds With Dates entity.
 */
@Repository
public interface CfarMedsWithDatesRepository extends JpaRepository<CfarMedsWithDates, String> {
}
