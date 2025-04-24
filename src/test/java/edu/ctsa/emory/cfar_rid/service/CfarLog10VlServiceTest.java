package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarLog10Vl;
import edu.ctsa.emory.cfar_rid.repository.CfarLog10VlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CfarLog10VlServiceTest {

    @Mock
    private CfarLog10VlRepository repository;

    @InjectMocks
    private CfarLog10VlService service;

    private CfarLog10Vl record;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        record = CfarLog10Vl.builder()
                .studyId("1001")
                .personKey("P001")
                .description("VL record")
                .log10VlLabResult("2.3")
                .build();
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(record));
        List<CfarLog10Vl> results = service.getAll();
        assertEquals(1, results.size());
    }

    @Test
    void testGetByIdFound() {
        when(repository.findById("1001")).thenReturn(Optional.of(record));
        Optional<CfarLog10Vl> result = service.getById("1001");
        assertTrue(result.isPresent());
    }

    @Test
    void testGetByIdNotFound() {
        when(repository.findById("9999")).thenReturn(Optional.empty());
        Optional<CfarLog10Vl> result = service.getById("9999");
        assertTrue(result.isEmpty());
    }

    @Test
    void testSave() {
        when(repository.save(record)).thenReturn(record);
        CfarLog10Vl saved = service.save(record);
        assertNotNull(saved);
        assertEquals("1001", saved.getStudyId());
    }

    @Test
    void testDeleteByIdExists() {
        when(repository.existsById("1001")).thenReturn(true);
        boolean result = service.deleteById("1001");
        assertTrue(result);
        verify(repository).deleteById("1001");
    }

    @Test
    void testDeleteByIdNotExists() {
        when(repository.existsById("9999")).thenReturn(false);
        boolean result = service.deleteById("9999");
        assertFalse(result);
        verify(repository, never()).deleteById("9999");
    }
}
