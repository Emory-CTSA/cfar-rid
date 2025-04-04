package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarAbsCd8Loinc;
import edu.ctsa.emory.cfar_rid.repository.CfarAbsCd8LoincRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CfarAbsCd8LoincServiceTest {

    private CfarAbsCd8LoincRepository repository;
    private CfarAbsCd8LoincService service;

    @BeforeEach
    void setup() {
        repository = mock(CfarAbsCd8LoincRepository.class);
        service = new CfarAbsCd8LoincService(repository);
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(new CfarAbsCd8Loinc()));
        assertEquals(1, service.getAll().size());
    }

    @Test
    void testGetById() {
        CfarAbsCd8Loinc entity = new CfarAbsCd8Loinc();
        entity.setStudyId("TEST_ID");
        when(repository.findById("TEST_ID")).thenReturn(Optional.of(entity));
        assertTrue(service.getById("TEST_ID").isPresent());
    }

    @Test
    void testSave() {
        CfarAbsCd8Loinc entity = new CfarAbsCd8Loinc();
        entity.setStudyId("TEST_ID");
        when(repository.save(entity)).thenReturn(entity);
        assertEquals("TEST_ID", service.save(entity).getStudyId());
    }

    @Test
    void testDeleteByIdSuccess() {
        when(repository.existsById("TEST_ID")).thenReturn(true);
        assertTrue(service.deleteById("TEST_ID"));
    }

    @Test
    void testDeleteByIdFailure() {
        when(repository.existsById("MISSING")).thenReturn(false);
        assertFalse(service.deleteById("MISSING"));
    }
}
