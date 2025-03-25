package edu.ctsa.emory.cfar_rid.entity;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "cfar_diagnosis", schema = "cfar_rid_hiv")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CfarDiagnosis {
    @Id
    @Column(name = "study_id")
    private String studyId;

    @Column(name = "person_key")
    private String personKey;

    @Column(name = "diagnosis_name", columnDefinition = "TEXT")
    private String diagnosisName;

    @Temporal(TemporalType.DATE)
    @Column(name = "contact_date")
    private Date contactDate;

    @Column(name = "days_from_hiv_dx_to_diagnosis")
    private Integer daysFromHivDxToDiagnosis;

    @Column(name = "icd10_code", columnDefinition = "TEXT")
    private String icd10Code;

    @Column(name = "icd9_code", columnDefinition = "TEXT")
    private String icd9Code;

    @Column(name = "nhs_code", columnDefinition = "TEXT")
    private String nhsCode;
}
