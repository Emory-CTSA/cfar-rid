package edu.ctsa.emory.cfar_rid.controller;

import edu.ctsa.emory.cfar_rid.entity.CFARReservoirMeasurements;
import edu.ctsa.emory.cfar_rid.service.CFARReservoirMeasurementsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CFARReservoirMeasurementsControllerTest {

    @Mock
    private CFARReservoirMeasurementsService service;

    @InjectMocks
    private CFARReservoirMeasurementsController controller;

    private CFARReservoirMeasurements sampleRecord;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleRecord = CFARReservoirMeasurements.builder()
                .personKey("P001")
                .studyId("S001")
                .build();
    }

    @Test
    void testGetAll() {
        when(service.getAll()).thenReturn(List.of(sampleRecord));

        ResponseEntity<?> response = controller.getAll();
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testGetByIdFound() {
        when(service.getById("P001")).thenReturn(Optional.of(sampleRecord));

        ResponseEntity<?> response = controller.getById("P001");
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testGetByIdNotFound() {
        when(service.getById("P001")).thenReturn(Optional.empty());

        ResponseEntity<?> response = controller.getById("P001");
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testSave() {
        when(service.save(sampleRecord)).thenReturn(sampleRecord);

        ResponseEntity<?> response = controller.save(sampleRecord);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testDeleteByIdFound() {
        when(service.deleteById("P001")).thenReturn(true);

        ResponseEntity<?> response = controller.delete("P001");
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testDeleteByIdNotFound() {
        when(service.deleteById("P001")).thenReturn(false);

        ResponseEntity<?> response = controller.delete("P001");
        assertEquals(404, response.getStatusCodeValue());
    }
}
