package edu.ctsa.emory.cfar_rid.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "cfar_arv_start_date", schema = "cfar_rid_hiv")
public class CfarArvStartDate {

    @Id
    @Column(name = "STUDY_ID")
    private String studyId;

    @Column(name = "PERSON_KEY")
    private String personKey;

    @Column(name = "DRUG_DESCRIPTION")
    private String drugDescription;

    @Column(name = "RXNORM")
    private Integer rxnorm;

    @Column(name = "ARV_START_DATE_MODIFIER")
    private Integer arvStartDateModifier;

    @Column(name = "ARV_START_DATE")
    @Temporal(TemporalType.DATE)
    private Date arvStartDate;

    @Column(name = "DAYS_FROM_HIV_DX_TO_ARV_START")
    private Integer daysFromHivDxToArvStart;

    @Column(name = "TIME_TO_ART")
    private Integer timeToArt;

    @Column(name = "TIME_ON_ART")
    private Integer timeOnArt;

    @Column(name = "TOTAL_NNRTI_TIME")
    private Integer totalNnrtiTime;

    @Column(name = "TOTAL_NRTI_TIME")
    private Integer totalNrtiTime;

    @Column(name = "TOTAL_PI_TIME")
    private Integer totalPiTime;

    @Column(name = "TOTAL_INSTI_TIME")
    private Integer totalInstiTime;

    @Column(name = "ATI_START_DATE")
    @Temporal(TemporalType.DATE)
    private Date atiStartDate;

    @Column(name = "ATI_STOP_DATE")
    @Temporal(TemporalType.DATE)
    private Date atiStopDate;
}
