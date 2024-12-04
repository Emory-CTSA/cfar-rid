package edu.ctsa.emory.cfar_rid.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "cfar_reservoir_measurements", schema = "cfar_rid_hiv")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CFARReservoirMeasurements {

    @Id
    @Column(name = "PERSON_KEY")
    private String personKey;

    @Column(name = "STUDY_ID")
    private String studyId;

    @Column(name = "SAMPLING_DATE")
    @Temporal(TemporalType.DATE)
    private Date samplingDate;

    @Column(name = "DAYS_FROM_HIV_DX_TO_SAMPLING")
    private Integer daysFromHivDxToSampling;

    @Column(name = "HIV_SUBTYPE")
    private String hivSubtype;

    @Column(name = "Total HIV DNA (cp/million CD4+)")
    private Float totalHivDna;

    @Column(name = "IPDA DSI corrected (cp/million CD4+)")
    private Float ipdaDsiCorrected;

    @Column(name = "Cells/µl")
    private String cellsPerMicroliter;

    @Column(name = "Volume input Rainbow")
    private String volumeInputRainbow;

    @Column(name = "Cells input in Rainbow per replicate")
    private Integer cellsInputInRainbowPerReplicate;

    @Column(name = "Number of replicates")
    private Integer numberOfReplicates;

    @Column(name = "DSI")
    private Float dsi;

    @Column(name = "Report based on total")
    private String reportBasedOnTotal;

    @Column(name = "IPDA (cp/million CD4+)")
    private Float ipda;

    @Column(name = "Report based on IPDA")
    private String reportBasedOnIpda;

    @Column(name = "Rainbow (cp/million CD4+ T cells)")
    private Float rainbowCpMillionCd4;

    @Column(name = "Report based on Rainbow")
    private String reportBasedOnRainbow;

    @Column(name = "Rainbow dsi corrrected (cp/million CD4+)")
    private String rainbowDsiCorrected;

    @Column(name = "drop outs")
    private String dropOuts;

    @Column(name = "Defective")
    private String defective;

    @Column(name = "5' Defective")
    private String fivePrimeDefective;

    @Column(name = "3' Defective")
    private String threePrimeDefective;

    @Column(name = "Total based on env and psi")
    private String totalBasedOnEnvAndPsi;

    @Column(name = "Total HIV DNA Naive")
    private String totalHivDnaNaive;

    @Column(name = "Total HIV DNA CM")
    private String totalHivDnaCm;

    @Column(name = "Total HIV DNA TM")
    private String totalHivDnaTm;

    @Column(name = "Total HIV DNA EM")
    private String totalHivDnaEm;

}

