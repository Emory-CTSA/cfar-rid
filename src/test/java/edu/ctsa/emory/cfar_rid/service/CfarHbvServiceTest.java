package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarHbv;
import edu.ctsa.emory.cfar_rid.repository.CfarHbvRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CfarHbvServiceTest {

    @Mock
    private CfarHbvRepository repository;

    @InjectMocks
    private CfarHbvService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        List<CfarHbv> list = List.of(new CfarHbv());
        when(repository.findAll()).thenReturn(list);

        List<CfarHbv> result = service.getAll();

        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetByIdFound() {
        String studyId = "123";
        CfarHbv hbv = new CfarHbv();
        when(repository.findById(studyId)).thenReturn(Optional.of(hbv));

        Optional<CfarHbv> result = service.getById(studyId);

        assertTrue(result.isPresent());
        verify(repository, times(1)).findById(studyId);
    }

    @Test
    void testGetByIdNotFound() {
        String studyId = "123";
        when(repository.findById(studyId)).thenReturn(Optional.empty());

        Optional<CfarHbv> result = service.getById(studyId);

        assertTrue(result.isEmpty());
        verify(repository, times(1)).findById(studyId);
    }

    @Test
    void testSave() {
        CfarHbv hbv = new CfarHbv();
        when(repository.save(hbv)).thenReturn(hbv);

        CfarHbv saved = service.save(hbv);

        assertNotNull(saved);
        verify(repository, times(1)).save(hbv);
    }

    @Test
    void testDeleteByIdExists() {
        String studyId = "001";
        when(repository.existsById(studyId)).thenReturn(true);

        boolean result = service.deleteById(studyId);

        assertTrue(result);
        verify(repository, times(1)).deleteById(studyId);
    }

    @Test
    void testDeleteByIdNotExists() {
        String studyId = "002";
        when(repository.existsById(studyId)).thenReturn(false);

        boolean result = service.deleteById(studyId);

        assertFalse(result);
        verify(repository, never()).deleteById(studyId);
    }
}
