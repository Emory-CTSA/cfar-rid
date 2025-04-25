package edu.ctsa.emory.cfar_rid.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity representing lymphocyte lab results from CFAR dataset.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "cfar_lymphocytes_all", schema = "cfar_rid_hiv")
public class CfarLymphocytesAll {

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
    private String result;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAB_DATE")
    private Date labDate;

    @Column(name = "LOINC_CODE")
    private String loincCode;
}
