package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CFARDemographics;
import edu.ctsa.emory.cfar_rid.repository.CFARDemographicsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CFARDemographicsServiceTest {

    private CFARDemographicsRepository repository;
    private CFARDemographicsService service;

    @BeforeEach
    void setUp() {
        repository = mock(CFARDemographicsRepository.class);
        service = new CFARDemographicsService(repository);
    }

    @Test
    void testGetAll() {
        List<CFARDemographics> demoList = List.of(new CFARDemographics());
        when(repository.findAll()).thenReturn(demoList);

        List<CFARDemographics> result = service.getAll();

        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetByIdFound() {
        String id = "STUDY123";
        CFARDemographics demo = new CFARDemographics();
        demo.setStudyId(id);
        when(repository.findById(id)).thenReturn(Optional.of(demo));

        Optional<CFARDemographics> result = service.getById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getStudyId());
    }

    @Test
    void testGetByIdNotFound() {
        String id = "NOT_FOUND";
        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<CFARDemographics> result = service.getById(id);

        assertFalse(result.isPresent());
    }

    @Test
    void testSave() {
        CFARDemographics demo = new CFARDemographics();
        demo.setStudyId("STUDY_SAVE");

        when(repository.save(demo)).thenReturn(demo);

        CFARDemographics saved = service.save(demo);

        assertNotNull(saved);
        assertEquals("STUDY_SAVE", saved.getStudyId());
    }

    @Test
    void testDeleteByIdSuccess() {
        String id = "STUDY_DELETE";
        when(repository.existsById(id)).thenReturn(true);

        boolean result = service.deleteById(id);

        assertTrue(result);
        verify(repository).deleteById(id);
    }

    @Test
    void testDeleteByIdFailure() {
        String id = "MISSING_ID";
        when(repository.existsById(id)).thenReturn(false);

        boolean result = service.deleteById(id);

        assertFalse(result);
        verify(repository, never()).deleteById(id);
    }
}
