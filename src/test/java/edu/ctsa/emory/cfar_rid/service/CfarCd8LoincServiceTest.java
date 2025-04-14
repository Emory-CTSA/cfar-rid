package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarCd8Loinc;
import edu.ctsa.emory.cfar_rid.repository.CfarCd8LoincRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CfarCd8LoincServiceTest {

    @Mock
    private CfarCd8LoincRepository repository;

    @InjectMocks
    private CfarCd8LoincService service;

    private CfarCd8Loinc sample;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sample = new CfarCd8Loinc();
        sample.setStudyId("STUDY001");
        sample.setPersonKey("PERSON001");
        sample.setResult(BigDecimal.valueOf(250));
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(sample));

        List<CfarCd8Loinc> result = service.getAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getStudyId()).isEqualTo("STUDY001");
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetByIdFound() {
        when(repository.findById("STUDY001")).thenReturn(Optional.of(sample));

        Optional<CfarCd8Loinc> result = service.getById("STUDY001");

        assertThat(result).isPresent();
        assertThat(result.get().getPersonKey()).isEqualTo("PERSON001");
        verify(repository, times(1)).findById("STUDY001");
    }

    @Test
    void testGetByIdNotFound() {
        when(repository.findById("NOT_FOUND")).thenReturn(Optional.empty());

        Optional<CfarCd8Loinc> result = service.getById("NOT_FOUND");

        assertThat(result).isEmpty();
        verify(repository).findById("NOT_FOUND");
    }

    @Test
    void testSave() {
        when(repository.save(any(CfarCd8Loinc.class))).thenReturn(sample);

        CfarCd8Loinc saved = service.save(sample);

        assertThat(saved.getStudyId()).isEqualTo("STUDY001");
        verify(repository).save(sample);
    }

    @Test
    void testDeleteByIdSuccess() {
        when(repository.existsById("STUDY001")).thenReturn(true);

        boolean result = service.deleteById("STUDY001");

        assertThat(result).isTrue();
        verify(repository).existsById("STUDY001");
        verify(repository).deleteById("STUDY001");
    }

    @Test
    void testDeleteByIdNotFound() {
        when(repository.existsById("MISSING")).thenReturn(false);

        boolean result = service.deleteById("MISSING");

        assertThat(result).isFalse();
        verify(repository).existsById("MISSING");
        verify(repository, never()).deleteById("MISSING");
    }
}
