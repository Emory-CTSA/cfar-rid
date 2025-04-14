package edu.ctsa.emory.cfar_rid.entity;

import javax.persistence.*;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cfar_cd8_loinc", schema = "cfar_rid_hiv")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CfarCd8Loinc {
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

    @Column(name = "cd8_at_sampling")
    private BigDecimal cd8AtSampling;

    @Column(name = "cd8_at_suppression")
    private BigDecimal cd8AtSuppression;

    @Column(name = "max_cd8")
    private BigDecimal maxCd8;
}
