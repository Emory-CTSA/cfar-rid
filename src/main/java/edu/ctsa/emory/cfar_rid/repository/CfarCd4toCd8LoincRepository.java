package edu.ctsa.emory.cfar_rid.repository;

import edu.ctsa.emory.cfar_rid.entity.CfarCd4toCd8Loinc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CfarCd4toCd8LoincRepository extends JpaRepository<CfarCd4toCd8Loinc, String> {
}
