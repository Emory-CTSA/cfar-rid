package edu.ctsa.emory.cfar_rid.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object representing CD4 count data by cohort and site.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CohortCd4DTO {
    private String cohort;
    private String site;
    private String cd4Count;
}

