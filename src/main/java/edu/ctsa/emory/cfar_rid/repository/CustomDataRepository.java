package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.dto.CohortCd4DTO;
import edu.ctsa.emory.cfar_rid.entity.CfarCd4Loinc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for custom data queries related to CD4 counts.
 */
@Repository
public interface CustomDataRepository extends CrudRepository<CfarCd4Loinc, Long> {

    /**
     * Fetches CD4 data by cohort and site, mapped directly to CohortCd4DTO.
     * @return List of Object[] arrays with [cohort, site, cd4Count]
     */
    @Query(value = """
        SELECT d.cohort, d.site, l.result AS cd4Count
        FROM cfar_rid_hiv.cfar_cd4_loinc l
        JOIN cfar_rid_hiv.cfar_demographics d
          ON l.study_id = d.study_id AND l.person_key = d.person_key
        ORDER BY d.cohort, d.site
    """, nativeQuery = true)
    List<Object[]> fetchCohortSiteCd4Raw();
}
