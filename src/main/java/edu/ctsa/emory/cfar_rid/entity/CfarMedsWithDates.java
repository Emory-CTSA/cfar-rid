package edu.ctsa.emory.cfar_rid.entity;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "cfar_meds_with_dates", schema = "cfar_rid_hiv")
public class CfarMedsWithDates {

    @Id
    @Column(name = "STUDY_ID")
    private String studyId;

    @Column(name = "PERSON_KEY")
    private String personKey;

    @Column(name = "DRUG_DESCRIPTION")
    private String drugDescription;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "RXNORM")
    private Integer rxnorm;

    @Column(name = "MEDICATION_CLASS")
    private String medicationClass;

    @Column(name = "MED_DURATION_DAYS")
    private Integer medDurationDays;
}
