package edu.ctsa.emory.cfar_rid.entity;

import edu.ctsa.emory.cfar_rid.embedded.CfarLymphocyteKey;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "cfar_lymphocytes_all", schema = "cfar_rid_hiv")
//@IdClass(CfarLymphocyteKey.class)
public class CfarLymphocytesAll {

    @Id
    @Column(name = "STUDY_ID")
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
