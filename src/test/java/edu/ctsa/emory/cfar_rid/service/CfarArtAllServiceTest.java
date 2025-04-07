package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarArtAll;
import edu.ctsa.emory.cfar_rid.repository.CfarArtAllRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CfarArtAllServiceTest {

    private CfarArtAllRepository repository;
    private CfarArtAllService service;

    @BeforeEach
    void setUp() {
        repository = mock(CfarArtAllRepository.class);
        service = new CfarArtAllService(repository);
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(new CfarArtAll()));
        List<CfarArtAll> result = service.getAll();
        assertEquals(1, result.size());
        verify(repository).findAll();
    }

    @Test
    void testGetByIdFound() {
        CfarArtAll record = new CfarArtAll();
        record.setStudyId("STUDY001");

        when(repository.findById("STUDY001")).thenReturn(Optional.of(record));
        Optional<CfarArtAll> result = service.getById("STUDY001");

        assertTrue(result.isPresent());
        assertEquals("STUDY001", result.get().getStudyId());
    }

    @Test
    void testGetByIdNotFound() {
        when(repository.findById("MISSING")).thenReturn(Optional.empty());
        Optional<CfarArtAll> result = service.getById("MISSING");

        assertFalse(result.isPresent());
    }

    @Test
    void testSave() {
        CfarArtAll record = new CfarArtAll();
        record.setStudyId("STUDY_SAVE");

        when(repository.save(record)).thenReturn(record);
        CfarArtAll saved = service.save(record);

        assertNotNull(saved);
        assertEquals("STUDY_SAVE", saved.getStudyId());
    }

    @Test
    void testDeleteByIdSuccess() {
        when(repository.existsById("STUDY001")).thenReturn(true);

        boolean result = service.deleteById("STUDY001");

        assertTrue(result);
        verify(repository).deleteById("STUDY001");
    }

    @Test
    void testDeleteByIdFailure() {
        when(repository.existsById("MISSING")).thenReturn(false);

        boolean result = service.deleteById("MISSING");

        assertFalse(result);
        verify(repository, never()).deleteById(any());
    }
}
