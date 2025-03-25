package edu.ctsa.emory.cfar_rid.entity;

import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cfar_cd4_loinc", schema = "cfar_rid_hiv")
public class CfarCd4Loinc {

    @Id
    @Column(name = "study_id")
    private String studyId;

    @Column(name = "person_key")
    private String personKey;

    @Column(name = "description")
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

    @Column(name = "cd4_at_sampling")
    private BigDecimal cd4AtSampling;

    @Column(name = "cd4_at_suppression")
    private BigDecimal cd4AtSuppression;

    @Column(name = "max_cd4")
    private BigDecimal maxCd4;
}
