package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarCd4toCd8Loinc;
import edu.ctsa.emory.cfar_rid.repository.CfarCd4toCd8LoincRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for CfarCd4toCd8LoincService.
 */
class CfarCd4toCd8LoincServiceTest {

    private CfarCd4toCd8LoincRepository repository;
    private CfarCd4toCd8LoincService service;

    @BeforeEach
    void setUp() {
        repository = mock(CfarCd4toCd8LoincRepository.class);
        service = new CfarCd4toCd8LoincService(repository);
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(new CfarCd4toCd8Loinc()));

        List<CfarCd4toCd8Loinc> result = service.getAll();

        assertEquals(1, result.size());
        verify(repository).findAll();
    }

    @Test
    void testGetByIdFound() {
        CfarCd4toCd8Loinc record = new CfarCd4toCd8Loinc();
        record.setStudyId("STUDY001");

        when(repository.findById("STUDY001")).thenReturn(Optional.of(record));

        Optional<CfarCd4toCd8Loinc> result = service.getById("STUDY001");

        assertTrue(result.isPresent());
        assertEquals("STUDY001", result.get().getStudyId());
    }

    @Test
    void testGetByIdNotFound() {
        when(repository.findById("MISSING")).thenReturn(Optional.empty());

        Optional<CfarCd4toCd8Loinc> result = service.getById("MISSING");

        assertFalse(result.isPresent());
    }

    @Test
    void testSave() {
        CfarCd4toCd8Loinc record = new CfarCd4toCd8Loinc();
        record.setStudyId("SAVE_ID");

        when(repository.save(record)).thenReturn(record);

        CfarCd4toCd8Loinc saved = service.save(record);

        assertEquals("SAVE_ID", saved.getStudyId());
        verify(repository).save(record);
    }

    @Test
    void testDeleteByIdSuccess() {
        when(repository.existsById("STUDY001")).thenReturn(true);

        boolean deleted = service.deleteById("STUDY001");

        assertTrue(deleted);
        verify(repository).deleteById("STUDY001");
    }

    @Test
    void testDeleteByIdFailure() {
        when(repository.existsById("MISSING")).thenReturn(false);

        boolean deleted = service.deleteById("MISSING");

        assertFalse(deleted);
        verify(repository, never()).deleteById(anyString());
    }
}
