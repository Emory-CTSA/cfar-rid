package edu.ctsa.emory.cfar_rid.entity;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "cfar_abs_cd4_loinc", schema = "cfar_rid_hiv")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CfarAbsCd4Loinc {

    @Id
    @Column(name = "STUDY_ID", nullable = false)
    private String studyId;

    @Column(name = "PERSON_KEY")
    private String personKey;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LAB")
    private String lab;

    @Column(name = "RESULT")
    private Integer result;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAB_DATE")
    private Date labDate;

    @Column(name = "DAYS_FROM_HIV_DX_TO_LAB")
    private Integer daysFromHivDxToLab;

    @Column(name = "LOINC_CODE")
    private String loincCode;

    @Column(name = "ABSOLUTE_CD4_AT_SAMPLING")
    private Integer absoluteCd4AtSampling;

    @Column(name = "ABSOLUTE_CD4_DIR")
    private Integer absoluteCd4Dir;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_DIR_CD4")
    private Date dateOfDirCd4;

    @Column(name = "DAYS_FROM_HIV_DX_TO_CD4_DIR")
    private Integer daysFromHivDxToCd4Dir;

    @Column(name = "ABSOLUTE_CD4_AT_SUPPRESSION")
    private Integer absoluteCd4AtSuppression;

    @Column(name = "MAX_ABS_CD4")
    private Integer maxAbsCd4;

    @Column(name = "TIME_TO_MAX_CD4")
    private Integer timeToMaxCd4;
}
