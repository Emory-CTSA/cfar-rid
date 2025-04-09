package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarCd4Loinc;
import edu.ctsa.emory.cfar_rid.repository.CfarCd4LoincRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for CfarCd4LoincService.
 */
class CfarCd4LoincServiceTest {

    private CfarCd4LoincRepository repository;
    private CfarCd4LoincService service;

    @BeforeEach
    void setUp() {
        repository = mock(CfarCd4LoincRepository.class);
        service = new CfarCd4LoincService(repository);
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(new CfarCd4Loinc()));
        assertEquals(1, service.getAll().size());
    }

    @Test
    void testGetByIdFound() {
        CfarCd4Loinc record = new CfarCd4Loinc();
        record.setStudyId("STUDY123");

        when(repository.findById("STUDY123")).thenReturn(Optional.of(record));

        Optional<CfarCd4Loinc> result = service.getById("STUDY123");
        assertTrue(result.isPresent());
        assertEquals("STUDY123", result.get().getStudyId());
    }

    @Test
    void testGetByIdNotFound() {
        when(repository.findById("INVALID")).thenReturn(Optional.empty());
        assertFalse(service.getById("INVALID").isPresent());
    }

    @Test
    void testSave() {
        CfarCd4Loinc entity = new CfarCd4Loinc();
        entity.setStudyId("NEWID");

        when(repository.save(entity)).thenReturn(entity);
        assertEquals("NEWID", service.save(entity).getStudyId());
    }

    @Test
    void testDeleteByIdSuccess() {
        when(repository.existsById("STUDY123")).thenReturn(true);
        assertTrue(service.deleteById("STUDY123"));
        verify(repository).deleteById("STUDY123");
    }

    @Test
    void testDeleteByIdFailure() {
        when(repository.existsById("INVALID")).thenReturn(false);
        assertFalse(service.deleteById("INVALID"));
        verify(repository, never()).deleteById(any());
    }
}
