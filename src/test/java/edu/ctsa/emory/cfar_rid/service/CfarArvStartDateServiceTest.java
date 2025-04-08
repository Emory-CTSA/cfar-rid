package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarArvStartDate;
import edu.ctsa.emory.cfar_rid.repository.CfarArvStartDateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CfarArvStartDateServiceTest {

    private CfarArvStartDateRepository repository;
    private CfarArvStartDateService service;

    @BeforeEach
    void setUp() {
        repository = mock(CfarArvStartDateRepository.class);
        service = new CfarArvStartDateService(repository);
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(new CfarArvStartDate()));

        List<CfarArvStartDate> result = service.getAll();

        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetByIdFound() {
        CfarArvStartDate record = new CfarArvStartDate();
        record.setStudyId("STUDY001");

        when(repository.findById("STUDY001")).thenReturn(Optional.of(record));

        Optional<CfarArvStartDate> result = service.getById("STUDY001");

        assertTrue(result.isPresent());
        assertEquals("STUDY001", result.get().getStudyId());
    }

    @Test
    void testGetByIdNotFound() {
        when(repository.findById("MISSING")).thenReturn(Optional.empty());

        Optional<CfarArvStartDate> result = service.getById("MISSING");

        assertFalse(result.isPresent());
    }

    @Test
    void testSave() {
        CfarArvStartDate record = new CfarArvStartDate();
        record.setStudyId("STUDY_SAVE");

        when(repository.save(record)).thenReturn(record);

        CfarArvStartDate saved = service.save(record);

        assertNotNull(saved);
        assertEquals("STUDY_SAVE", saved.getStudyId());
    }

    @Test
    void testDeleteByIdSuccess() {
        when(repository.existsById("STUDY001")).thenReturn(true);

        boolean deleted = service.deleteById("STUDY001");

        assertTrue(deleted);
        verify(repository).deleteById("STUDY001");
    }

    @Test
    void testDeleteByIdNotFound() {
        when(repository.existsById("MISSING")).thenReturn(false);

        boolean deleted = service.deleteById("MISSING");

        assertFalse(deleted);
        verify(repository, never()).deleteById(anyString());
    }
}
