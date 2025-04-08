package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarCbcChemistry;
import edu.ctsa.emory.cfar_rid.repository.CfarCbcChemistryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for CfarCbcChemistryService.
 */
class CfarCbcChemistryServiceTest {

    private CfarCbcChemistryRepository repository;
    private CfarCbcChemistryService service;

    @BeforeEach
    void setUp() {
        repository = mock(CfarCbcChemistryRepository.class);
        service = new CfarCbcChemistryService(repository);
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(new CfarCbcChemistry()));

        List<CfarCbcChemistry> result = service.getAll();

        assertEquals(1, result.size());
        verify(repository).findAll();
    }

    @Test
    void testGetByIdFound() {
        CfarCbcChemistry record = new CfarCbcChemistry();
        record.setStudyId("STUDY001");

        when(repository.findById("STUDY001")).thenReturn(Optional.of(record));

        Optional<CfarCbcChemistry> result = service.getById("STUDY001");

        assertTrue(result.isPresent());
        assertEquals("STUDY001", result.get().getStudyId());
    }

    @Test
    void testGetByIdNotFound() {
        when(repository.findById("MISSING")).thenReturn(Optional.empty());

        Optional<CfarCbcChemistry> result = service.getById("MISSING");

        assertFalse(result.isPresent());
    }

    @Test
    void testSave() {
        CfarCbcChemistry record = new CfarCbcChemistry();
        record.setStudyId("SAVE_ID");

        when(repository.save(record)).thenReturn(record);

        CfarCbcChemistry saved = service.save(record);

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
