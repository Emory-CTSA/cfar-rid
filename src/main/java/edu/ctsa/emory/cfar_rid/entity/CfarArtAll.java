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
@Table(name = "cfar_art_all", schema = "cfar_rid_hiv")
public class CfarArtAll {

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

    @Column(name = "DAYS_FROM_HIV_DX_TO_DRUG_START")
    private Integer daysFromHivDxToDrugStart;

    @Column(name = "DAYS_FROM_HIV_DX_TO_DRUG_STOP")
    private Integer daysFromHivDxToDrugStop;

    @Column(name = "RXNORM")
    private Integer rxnorm;
}
