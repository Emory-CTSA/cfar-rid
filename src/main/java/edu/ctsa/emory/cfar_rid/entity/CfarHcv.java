package edu.ctsa.emory.cfar_rid.entity;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * Entity representing Hepatitis C Virus (HCV) lab data from CFAR.
 */
@Entity
@Table(name = "cfar_hcv", schema = "cfar_rid_hiv")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CfarHcv {

    @Id
    @Column(name = "study_id", nullable = false)
    private String studyId;

    @Column(name = "person_key")
    private String personKey;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "lab")
    private String lab;

    @Column(name = "result")
    private String result;

    @Temporal(TemporalType.DATE)
    @Column(name = "lab_date")
    private Date labDate;

    @Column(name = "days_from_hiv_dx_to_lab")
    private Integer daysFromHivDxToLab;

    @Column(name = "loinc")
    private String loinc;
}
