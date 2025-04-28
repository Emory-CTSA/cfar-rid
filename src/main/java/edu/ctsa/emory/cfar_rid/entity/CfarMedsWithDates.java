package edu.ctsa.emory.cfar_rid.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity representing medication records with start and end dates from CFAR dataset.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "cfar_meds_with_dates", schema = "cfar_rid_hiv")
public class CfarMedsWithDates {

    @Id
    @Column(name = "STUDY_ID", nullable = false)
    private String studyId;

    @Column(name = "PERSON_KEY")
    private String personKey;

    @Column(name = "DRUG_DESCRIPTION")
    private String drugDescription;

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "RXNORM")
    private Integer rxnorm;

    @Column(name = "MEDICATION_CLASS")
    private String medicationClass;

    @Column(name = "MED_DURATION_DAYS")
    private Integer medDurationDays;
}
