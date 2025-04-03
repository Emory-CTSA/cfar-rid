package edu.ctsa.emory.cfar_rid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctsa.emory.cfar_rid.entity.CfarAbsCd4Loinc;
import edu.ctsa.emory.cfar_rid.service.CfarAbsCd4LoincService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Web layer unit tests for CfarAbsCd4LoincController.
 */
@WebMvcTest(CfarAbsCd4LoincController.class)
class CfarAbsCd4LoincControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CfarAbsCd4LoincService service;

    @Autowired
    private ObjectMapper objectMapper;

    private CfarAbsCd4Loinc sample;

    @BeforeEach
    void setup() {
        sample = new CfarAbsCd4Loinc();
        sample.setStudyId("STUDY001");
        sample.setPersonKey("PERSON001");
        sample.setLab("LabX");
    }

    @Test
    void testGetAll() throws Exception {
        when(service.getAll()).thenReturn(List.of(sample));

        mockMvc.perform(get("/api/abs-cd4-loinc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void testGetByIdFound() throws Exception {
        when(service.getById("STUDY001")).thenReturn(Optional.of(sample));

        mockMvc.perform(get("/api/abs-cd4-loinc/STUDY001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("STUDY001"));
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(service.getById("MISSING")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/abs-cd4-loinc/MISSING"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSave() throws Exception {
        when(service.save(any(CfarAbsCd4Loinc.class))).thenReturn(sample);

        mockMvc.perform(post("/api/abs-cd4-loinc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sample)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("STUDY001"));
    }

    @Test
    void testDeleteSuccess() throws Exception {
        when(service.deleteById("STUDY001")).thenReturn(true);

        mockMvc.perform(delete("/api/abs-cd4-loinc/STUDY001"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteNotFound() throws Exception {
        when(service.deleteById("MISSING")).thenReturn(false);

        mockMvc.perform(delete("/api/abs-cd4-loinc/MISSING"))
                .andExpect(status().isNotFound());
    }
}
