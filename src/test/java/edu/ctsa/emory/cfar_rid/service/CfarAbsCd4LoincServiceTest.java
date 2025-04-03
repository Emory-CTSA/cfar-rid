package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarAbsCd4Loinc;
import edu.ctsa.emory.cfar_rid.repository.CfarAbsCd4LoincRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CfarAbsCd4LoincServiceTest {

    private CfarAbsCd4LoincRepository repository;
    private CfarAbsCd4LoincService service;

    @BeforeEach
    void setUp() {
        repository = mock(CfarAbsCd4LoincRepository.class);
        service = new CfarAbsCd4LoincService(repository);
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(new CfarAbsCd4Loinc()));

        List<CfarAbsCd4Loinc> result = service.getAll();

        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetByIdFound() {
        String id = "STUDY001";
        CfarAbsCd4Loinc entity = new CfarAbsCd4Loinc();
        entity.setStudyId(id);

        when(repository.findById(id)).thenReturn(Optional.of(entity));

        Optional<CfarAbsCd4Loinc> result = service.getById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getStudyId());
    }

    @Test
    void testGetByIdNotFound() {
        when(repository.findById("MISSING")).thenReturn(Optional.empty());

        Optional<CfarAbsCd4Loinc> result = service.getById("MISSING");

        assertFalse(result.isPresent());
    }

    @Test
    void testSave() {
        CfarAbsCd4Loinc entity = new CfarAbsCd4Loinc();
        entity.setStudyId("STUDY_SAVE");

        when(repository.save(entity)).thenReturn(entity);

        CfarAbsCd4Loinc saved = service.save(entity);

        assertNotNull(saved);
        assertEquals("STUDY_SAVE", saved.getStudyId());
    }

    @Test
    void testDeleteByIdSuccess() {
        String id = "STUDY001";
        when(repository.existsById(id)).thenReturn(true);

        boolean result = service.deleteById(id);

        assertTrue(result);
        verify(repository).deleteById(id);
    }

    @Test
    void testDeleteByIdFailure() {
        when(repository.existsById("MISSING")).thenReturn(false);

        boolean result = service.deleteById("MISSING");

        assertFalse(result);
        verify(repository, never()).deleteById(any());
    }
}
