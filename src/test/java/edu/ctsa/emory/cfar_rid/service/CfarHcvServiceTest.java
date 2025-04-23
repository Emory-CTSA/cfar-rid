package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarHcv;
import edu.ctsa.emory.cfar_rid.repository.CfarHcvRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CfarHcvServiceTest {

    @Mock
    private CfarHcvRepository repository;

    @InjectMocks
    private CfarHcvService service;

    private CfarHcv hcv;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hcv = new CfarHcv("123", "key123", "desc", "lab", "result", new Date(), 10, "loinc123");
    }

    @Test
    void getAllReturnsList() {
        when(repository.findAll()).thenReturn(List.of(hcv));
        assertEquals(1, service.getAll().size());
    }

    @Test
    void getByIdFound() {
        when(repository.findById("123")).thenReturn(Optional.of(hcv));
        assertTrue(service.getById("123").isPresent());
    }

    @Test
    void getByIdNotFound() {
        when(repository.findById("999")).thenReturn(Optional.empty());
        assertTrue(service.getById("999").isEmpty());
    }

    @Test
    void saveReturnsEntity() {
        when(repository.save(hcv)).thenReturn(hcv);
        assertEquals(hcv, service.save(hcv));
    }

    @Test
    void deleteByIdSuccess() {
        when(repository.existsById("123")).thenReturn(true);
        boolean result = service.deleteById("123");
        assertTrue(result);
        verify(repository).deleteById("123");
    }

    @Test
    void deleteByIdNotFound() {
        when(repository.existsById("999")).thenReturn(false);
        boolean result = service.deleteById("999");
        assertFalse(result);
        verify(repository, never()).deleteById("999");
    }
}
