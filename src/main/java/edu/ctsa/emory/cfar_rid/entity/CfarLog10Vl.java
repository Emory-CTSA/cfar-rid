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
@Table(name = "cfar_log10_vl", schema = "cfar_rid_hiv")
public class CfarLog10Vl {

    @Id
    @Column(name = "STUDY_ID")
    private String studyId;

    @Column(name = "PERSON_KEY")
    private String personKey;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LAB")
    private String lab;

    @Column(name = "VL_ASSAY")
    private String vlAssay;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAB_DATE")
    private Date labDate;

    @Column(name = "DAYS_FROM_HIV_DX_TO_LAB")
    private Integer daysFromHivDxToLab;

    @Column(name = "LOG10_VL_LAB_RESULT")
    private String log10VlLabResult;

    @Column(name = "LOG10_VL_LAB_NUMERIC_VALUE")
    private Double log10VlLabNumericValue;

    @Column(name = "LOG10_VL_QUALIFIER")
    private String log10VlQualifier;

    @Column(name = "LOG10_VL_NUMBERS_IMPUTED")
    private Double log10VlNumbersImputed;

    @Column(name = "LLOQ")
    private Double lloq;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_FIRST_SUPPRESSION")
    private Date dateOfFirstSuppression;

    @Column(name = "DAYS_FROM_HIV_DX_TO_FIRST_SUPPRESSION")
    private Integer daysFromHivDxToFirstSuppression;

    @Column(name = "TIME_SUPPRESSED")
    private Integer timeSuppressed;

    @Column(name = "TIME_TO_SUPPRESSION_QUALIFIER")
    private String timeToSuppressionQualifier;

    @Column(name = "TIME_TO_SUPPRESSION")
    private Integer timeToSuppression;

    @Column(name = "TIME_TO_SUPPRESSION_FROM_FIRST_VL_OBSERVED")
    private Integer timeToSuppressionFromFirstVlObserved;

    @Column(name = "TIME_UNDETECTABLE")
    private String timeUndetectable;

    @Column(name = "TIME_WITHOUT_VIREMIA")
    private String timeWithoutViremia;

    @Column(name = "TIME_OF_VIREMIA_PEAK")
    private String timeOfViremiaPeak;

    @Column(name = "MAXIMUM_VIREMIA_PEAK_AFTER_SUPPRESSION")
    private String maximumViremiaPeakAfterSuppression;

    @Column(name = "MAXIMUM_LOG10_VL_EVER")
    private Double maximumLog10VlEver;

    @Column(name = "LOG10_VL_AUC_ALL")
    private String log10VlAucAll;

    @Column(name = "LOG10_VL_AUC_AFTER_SUPPRESSION")
    private String log10VlAucAfterSuppression;
}

