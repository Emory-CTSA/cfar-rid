package edu.ctsa.emory.cfar_rid.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity class for cfar_reservoir_measurements table (schema: cfar_rid_hiv).
 */
@Entity
@Table(name = "cfar_reservoir_measurements", schema = "cfar_rid_hiv")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CFARReservoirMeasurements {

    @Id
    @Column(name = "person_key", nullable = false)
    private String personKey;

    @Column(name = "study_id")
    private String studyId;

    @Temporal(TemporalType.DATE)
    @Column(name = "sampling_date")
    private Date samplingDate;

    @Column(name = "days_from_hiv_dx_to_sampling")
    private Integer daysFromHivDxToSampling;

    @Column(name = "hiv_subtype")
    private String hivSubtype;

    @Column(name = "\"Total HIV DNA (cp/million CD4+)\"")
    private Float totalHivDna;

    @Column(name = "\"IPDA DSI corrected (cp/million CD4+)\"")
    private Float ipdaDsiCorrected;

    @Column(name = "\"Cells/µl\"")
    private String cellsPerMicroliter;

    @Column(name = "\"Volume input Rainbow\"")
    private String volumeInputRainbow;

    @Column(name = "\"Cells input in Rainbow per replicate\"")
    private Integer cellsInputInRainbowPerReplicate;

    @Column(name = "\"Number of replicates\"")
    private Integer numberOfReplicates;

    @Column(name = "dsi")
    private Float dsi;

    @Column(name = "\"Report based on total\"")
    private String reportBasedOnTotal;

    @Column(name = "\"IPDA (cp/million CD4+)\"")
    private Float ipda;

    @Column(name = "\"Report based on IPDA\"")
    private String reportBasedOnIpda;

    @Column(name = "\"Rainbow (cp/million CD4+ T cells)\"")
    private Float rainbowCpMillionCd4;

    @Column(name = "\"Report based on Rainbow\"")
    private String reportBasedOnRainbow;

    @Column(name = "\"Rainbow dsi corrrected (cp/million CD4+)\"")
    private String rainbowDsiCorrected;

    @Column(name = "\"drop outs\"")
    private String dropOuts;

    @Column(name = "defective")
    private String defective;

    @Column(name = "\"5' Defective\"")
    private String fivePrimeDefective;

    @Column(name = "\"3' Defective\"")
    private String threePrimeDefective;

    @Column(name = "\"Total based on env and psi\"")
    private String totalBasedOnEnvAndPsi;

    @Column(name = "\"Total HIV DNA Naive\"")
    private String totalHivDnaNaive;

    @Column(name = "\"Total HIV DNA CM\"")
    private String totalHivDnaCm;

    @Column(name = "\"Total HIV DNA TM\"")
    private String totalHivDnaTm;

    @Column(name = "\"Total HIV DNA EM\"")
    private String totalHivDnaEm;
}
