package edu.ctsa.emory.cfar_rid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctsa.emory.cfar_rid.entity.CfarCbcChemistry;
import edu.ctsa.emory.cfar_rid.service.CfarCbcChemistryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Web layer unit tests for CfarCbcChemistryController.
 */
@WebMvcTest(CfarCbcChemistryController.class)
class CfarCbcChemistryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CfarCbcChemistryService service;

    @Autowired
    private ObjectMapper objectMapper;

    private CfarCbcChemistry sample;

    @BeforeEach
    void setup() {
        sample = new CfarCbcChemistry();
        sample.setStudyId("STUDY001");
        sample.setPersonKey("PERSON001");
        sample.setResult("Positive");
    }

    @Test
    void testGetAll() throws Exception {
        when(service.getAll()).thenReturn(List.of(sample));

        mockMvc.perform(get("/api/cbc-chemistry"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void testGetByIdFound() throws Exception {
        when(service.getById("STUDY001")).thenReturn(Optional.of(sample));

        mockMvc.perform(get("/api/cbc-chemistry/STUDY001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("STUDY001"));
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(service.getById("NOT_FOUND")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/cbc-chemistry/NOT_FOUND"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSave() throws Exception {
        when(service.save(any(CfarCbcChemistry.class))).thenReturn(sample);

        mockMvc.perform(post("/api/cbc-chemistry")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sample)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("STUDY001"));
    }

    @Test
    void testDeleteSuccess() throws Exception {
        when(service.deleteById("STUDY001")).thenReturn(true);

        mockMvc.perform(delete("/api/cbc-chemistry/STUDY001"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteNotFound() throws Exception {
        when(service.deleteById("NOT_FOUND")).thenReturn(false);

        mockMvc.perform(delete("/api/cbc-chemistry/NOT_FOUND"))
                .andExpect(status().isNotFound());
    }
}
