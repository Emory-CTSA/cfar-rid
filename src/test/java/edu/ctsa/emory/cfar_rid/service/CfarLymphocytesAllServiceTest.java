package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarLymphocytesAll;
import edu.ctsa.emory.cfar_rid.repository.CfarLymphocytesAllRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CfarLymphocytesAllServiceTest {

    @Mock
    private CfarLymphocytesAllRepository repository;

    @InjectMocks
    private CfarLymphocytesAllService service;

    private CfarLymphocytesAll record;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        record = CfarLymphocytesAll.builder()
                .studyId("LY001")
                .personKey("P001")
                .description("Lymphocyte test")
                .lab("LabCorp")
                .result("Normal")
                .loincCode("12345-6")
                .labDate(new Date())
                .build();
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(record));
        List<CfarLymphocytesAll> result = service.getAll();
        assertEquals(1, result.size());
    }

    @Test
    void testGetByIdFound() {
        when(repository.findById("LY001")).thenReturn(Optional.of(record));
        Optional<CfarLymphocytesAll> result = service.getById("LY001");
        assertTrue(result.isPresent());
    }

    @Test
    void testGetByIdNotFound() {
        when(repository.findById("LY999")).thenReturn(Optional.empty());
        Optional<CfarLymphocytesAll> result = service.getById("LY999");
        assertTrue(result.isEmpty());
    }

    @Test
    void testSave() {
        when(repository.save(record)).thenReturn(record);
        CfarLymphocytesAll saved = service.save(record);
        assertNotNull(saved);
        assertEquals("LY001", saved.getStudyId());
    }

    @Test
    void testDeleteByIdExists() {
        when(repository.existsById("LY001")).thenReturn(true);
        boolean result = service.deleteById("LY001");
        assertTrue(result);
        verify(repository).deleteById("LY001");
    }

    @Test
    void testDeleteByIdNotExists() {
        when(repository.existsById("LY999")).thenReturn(false);
        boolean result = service.deleteById("LY999");
        assertFalse(result);
        verify(repository, never()).deleteById("LY999");
    }
}
