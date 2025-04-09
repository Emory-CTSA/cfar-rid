package edu.ctsa.emory.cfar_rid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctsa.emory.cfar_rid.entity.CfarCd4Loinc;
import edu.ctsa.emory.cfar_rid.service.CfarCd4LoincService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Web layer unit tests for CfarCd4LoincController.
 */
@WebMvcTest(CfarCd4LoincController.class)
class CfarCd4LoincControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CfarCd4LoincService service;

    @Autowired
    private ObjectMapper objectMapper;

    private CfarCd4Loinc sample;

    @BeforeEach
    void setUp() {
        sample = new CfarCd4Loinc();
        sample.setStudyId("STUDY001");
        sample.setResult(BigDecimal.valueOf(500));
    }

    @Test
    void testGetAll() throws Exception {
        when(service.getAll()).thenReturn(List.of(sample));

        mockMvc.perform(get("/api/cd4-loinc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studyId").value("STUDY001"));
    }

    @Test
    void testGetByIdFound() throws Exception {
        when(service.getById("STUDY001")).thenReturn(Optional.of(sample));

        mockMvc.perform(get("/api/cd4-loinc/STUDY001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("STUDY001"));
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(service.getById("NOT_FOUND")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/cd4-loinc/NOT_FOUND"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSave() throws Exception {
        when(service.save(any(CfarCd4Loinc.class))).thenReturn(sample);

        mockMvc.perform(post("/api/cd4-loinc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sample)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("STUDY001"));
    }

    @Test
    void testDeleteSuccess() throws Exception {
        when(service.deleteById("STUDY001")).thenReturn(true);

        mockMvc.perform(delete("/api/cd4-loinc/STUDY001"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteNotFound() throws Exception {
        when(service.deleteById("MISSING")).thenReturn(false);

        mockMvc.perform(delete("/api/cd4-loinc/MISSING"))
                .andExpect(status().isNotFound());
    }
}
