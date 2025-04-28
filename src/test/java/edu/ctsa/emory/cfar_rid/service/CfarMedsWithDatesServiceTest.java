package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CfarMedsWithDates;
import edu.ctsa.emory.cfar_rid.repository.CfarMedsWithDatesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CfarMedsWithDatesServiceTest {

    @Mock
    private CfarMedsWithDatesRepository repository;

    @InjectMocks
    private CfarMedsWithDatesService service;

    private CfarMedsWithDates record;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        record = CfarMedsWithDates.builder()
                .studyId("MED001")
                .personKey("P123")
                .drugDescription("Abacavir")
                .startDate(new Date())
                .endDate(new Date())
                .rxnorm(123456)
                .medicationClass("NRTI")
                .medDurationDays(30)
                .build();
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(record));
        List<CfarMedsWithDates> result = service.getAll();
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetByIdFound() {
        when(repository.findById("MED001")).thenReturn(Optional.of(record));
        Optional<CfarMedsWithDates> result = service.getById("MED001");
        assertTrue(result.isPresent());
        assertEquals("MED001", result.get().getStudyId());
    }

    @Test
    void testGetByIdNotFound() {
        when(repository.findById("MED999")).thenReturn(Optional.empty());
        Optional<CfarMedsWithDates> result = service.getById("MED999");
        assertFalse(result.isPresent());
    }

    @Test
    void testSave() {
        when(repository.save(record)).thenReturn(record);
        CfarMedsWithDates saved = service.save(record);
        assertNotNull(saved);
        assertEquals("Abacavir", saved.getDrugDescription());
    }

    @Test
    void testDeleteByIdExists() {
        when(repository.existsById("MED001")).thenReturn(true);
        boolean result = service.deleteById("MED001");
        assertTrue(result);
        verify(repository).deleteById("MED001");
    }

    @Test
    void testDeleteByIdNotExists() {
        when(repository.existsById("MED999")).thenReturn(false);
        boolean result = service.deleteById("MED999");
        assertFalse(result);
        verify(repository, never()).deleteById("MED999");
    }
}
