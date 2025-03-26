package edu.ctsa.emory.cfar_rid.entity;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * Entity class representing CFAR Demographics data.
 */
@Entity
@Table(name = "cfar_demographics", schema = "cfar_rid_hiv")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CFARDemographics {

    @Id
    @Column(name = "study_id", nullable = false)
    private String studyId;

    @Column(name = "person_key", nullable = false)
    private String personKey;

    @Column(name = "site")
    private String site;

    @Column(name = "cohort")
    private String cohort;

    @Column(name = "age_at_sampling")
    private String ageAtSampling;

    @Column(name = "age_at_hiv_diagnosis")
    private String ageAtHivDiagnosis;

    @Column(name = "age_at_art_start")
    private String ageAtArtStart;

    @Column(name = "sex")
    private String sex;

    @Column(name = "hiv_positive_date_modifier")
    private Integer hivPositiveDateModifier;

    @Temporal(TemporalType.DATE)
    @Column(name = "hiv_positive_date")
    private Date hivPositiveDate;

    @Column(name = "hiv_positive_year")
    private Integer hivPositiveYear;

    @Column(name = "estimated_seroconversion_timepoint")
    private Integer estimatedSeroconversionTimepoint;

    @Column(name = "race")
    private String race;

    @Column(name = "ethnicity")
    private String ethnicity;

    @Temporal(TemporalType.DATE)
    @Column(name = "first_clinic_visit")
    private Date firstClinicVisit;

    @Column(name = "days_from_hiv_pos_date_to_first_clinic_visit")
    private Integer daysFromHivPosDateToFirstClinicVisit;

    @Column(name = "hiv_duration")
    private Integer hivDuration;
}
