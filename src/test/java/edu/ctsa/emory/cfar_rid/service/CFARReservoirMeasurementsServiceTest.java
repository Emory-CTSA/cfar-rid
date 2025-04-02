package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.entity.CFARReservoirMeasurements;
import edu.ctsa.emory.cfar_rid.repository.CFARReservoirMeasurementsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CFARReservoirMeasurementsServiceTest {

    @Mock
    private CFARReservoirMeasurementsRepository repository;

    @InjectMocks
    private CFARReservoirMeasurementsService service;

    private CFARReservoirMeasurements sampleRecord;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleRecord = CFARReservoirMeasurements.builder()
                .personKey("P001")
                .studyId("S001")
                .hivSubtype("A1")
                .build();
    }

    @Test
    void testGetAll() {
        List<CFARReservoirMeasurements> mockList = List.of(sampleRecord);
        when(repository.findAll()).thenReturn(mockList);

        List<CFARReservoirMeasurements> result = service.getAll();
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetById() {
        when(repository.findById("P001")).thenReturn(Optional.of(sampleRecord));

        Optional<CFARReservoirMeasurements> result = service.getById("P001");
        assertTrue(result.isPresent());
        assertEquals("S001", result.get().getStudyId());
    }

    @Test
    void testSave() {
        when(repository.save(sampleRecord)).thenReturn(sampleRecord);

        CFARReservoirMeasurements saved = service.save(sampleRecord);
        assertEquals("S001", saved.getStudyId());
    }

    @Test
    void testDeleteByIdWhenExists() {
        when(repository.existsById("P001")).thenReturn(true);
        doNothing().when(repository).deleteById("P001");

        boolean deleted = service.deleteById("P001");
        assertTrue(deleted);
        verify(repository).deleteById("P001");
    }

    @Test
    void testDeleteByIdWhenNotExists() {
        when(repository.existsById("P001")).thenReturn(false);

        boolean deleted = service.deleteById("P001");
        assertFalse(deleted);
        verify(repository, never()).deleteById("P001");
    }
}
