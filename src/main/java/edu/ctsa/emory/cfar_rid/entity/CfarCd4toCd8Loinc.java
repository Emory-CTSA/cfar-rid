package edu.ctsa.emory.cfar_rid.entity;

import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cfar_cd4tocd8_loinc", schema = "cfar_rid_hiv")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CfarCd4toCd8Loinc {

    @Id
    @Column(name = "study_id")
    private String studyId;

    @Column(name = "person_key")
    private String personKey;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "lab")
    private String lab;

    @Column(name = "result")
    private BigDecimal result;

    @Temporal(TemporalType.DATE)
    @Column(name = "lab_date")
    private Date labDate;

    @Column(name = "days_from_hiv_dx_to_lab")
    private Integer daysFromHivDxToLab;

    @Column(name = "loinc_code")
    private String loincCode;

    @Column(name = "cd4_cd8_ratio_at_sampling")
    private BigDecimal cd4Cd8RatioAtSampling;

    @Column(name = "cd4_cd8_ratio_at_suppression")
    private BigDecimal cd4Cd8RatioAtSuppression;

    @Column(name = "max_cd4_cd8_ratio")
    private BigDecimal maxCd4Cd8Ratio;

    @Column(name = "min_cd4_cd8_ratio")
    private BigDecimal minCd4Cd8Ratio;
}
