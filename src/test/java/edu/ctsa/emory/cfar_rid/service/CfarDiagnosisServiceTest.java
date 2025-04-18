package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarDiagnosis;
import edu.ctsa.emory.cfar_rid.repository.CfarDiagnosisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CfarDiagnosisServiceTest {

    private CfarDiagnosisRepository repository;
    private CfarDiagnosisService service;

    private CfarDiagnosis sample;

    @BeforeEach
    void setUp() {
        repository = mock(CfarDiagnosisRepository.class);
        service = new CfarDiagnosisService(repository);

        sample = new CfarDiagnosis();
        sample.setStudyId("STUDY001");
        sample.setDiagnosisName("Tuberculosis");
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(sample));
        List<CfarDiagnosis> result = service.getAll();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getDiagnosisName()).isEqualTo("Tuberculosis");
    }

    @Test
    void testGetByIdFound() {
        when(repository.findById("STUDY001")).thenReturn(Optional.of(sample));
        Optional<CfarDiagnosis> result = service.getById("STUDY001");
        assertThat(result).isPresent();
        assertThat(result.get().getStudyId()).isEqualTo("STUDY001");
    }

    @Test
    void testGetByIdNotFound() {
        when(repository.findById("MISSING")).thenReturn(Optional.empty());
        Optional<CfarDiagnosis> result = service.getById("MISSING");
        assertThat(result).isNotPresent();
    }

    @Test
    void testSave() {
        when(repository.save(any())).thenReturn(sample);
        CfarDiagnosis saved = service.save(sample);
        assertThat(saved.getStudyId()).isEqualTo("STUDY001");
    }

    @Test
    void testDeleteByIdSuccess() {
        when(repository.existsById("STUDY001")).thenReturn(true);
        boolean deleted = service.deleteById("STUDY001");
        assertThat(deleted).isTrue();
        verify(repository).deleteById("STUDY001");
    }

    @Test
    void testDeleteByIdNotFound() {
        when(repository.existsById("MISSING")).thenReturn(false);
        boolean deleted = service.deleteById("MISSING");
        assertThat(deleted).isFalse();
        verify(repository, never()).deleteById("MISSING");
    }
}
