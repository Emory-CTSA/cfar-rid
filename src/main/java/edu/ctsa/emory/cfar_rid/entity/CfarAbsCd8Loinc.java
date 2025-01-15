package edu.ctsa.emory.cfar_rid.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "cfar_abs_cd8_loinc", schema = "cfar_rid_hiv")
public class CfarAbsCd8Loinc {

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

    @Column(name = "abs_cd8_at_sampling")
    private Integer absCd8AtSampling;

    @Column(name = "cd8_dir")
    private Integer cd8Dir;

    @Column(name = "abs_cd8_at_suppression")
    private Integer absCd8AtSuppression;

    @Column(name = "max_abs_cd8")
    private BigDecimal maxAbsCd8;
}
