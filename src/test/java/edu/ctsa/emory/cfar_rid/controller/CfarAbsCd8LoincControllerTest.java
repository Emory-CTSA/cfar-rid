package edu.ctsa.emory.cfar_rid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctsa.emory.cfar_rid.entity.CfarAbsCd8Loinc;
import edu.ctsa.emory.cfar_rid.service.CfarAbsCd8LoincService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(CfarAbsCd8LoincController.class)
class CfarAbsCd8LoincControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CfarAbsCd8LoincService service;

    @Autowired
    private ObjectMapper objectMapper;

    private CfarAbsCd8Loinc sample;

    @BeforeEach
    void setup() {
        sample = new CfarAbsCd8Loinc();
        sample.setStudyId("CD8TEST");
    }

    @Test
    void testGetAll() throws Exception {
        when(service.getAll()).thenReturn(List.of(sample));
        mockMvc.perform(get("/api/abs-cd8-loinc")).andExpect(status().isOk());
    }

    @Test
    void testGetByIdFound() throws Exception {
        when(service.getById("CD8TEST")).thenReturn(Optional.of(sample));
        mockMvc.perform(get("/api/abs-cd8-loinc/CD8TEST"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("CD8TEST"));
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(service.getById("MISSING")).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/abs-cd8-loinc/MISSING"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSave() throws Exception {
        when(service.save(any())).thenReturn(sample);
        mockMvc.perform(post("/api/abs-cd8-loinc")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(sample)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteSuccess() throws Exception {
        when(service.deleteById("CD8TEST")).thenReturn(true);
        mockMvc.perform(delete("/api/abs-cd8-loinc/CD8TEST")).andExpect(status().isNoContent());
    }

    @Test
    void testDeleteNotFound() throws Exception {
        when(service.deleteById("MISSING")).thenReturn(false);
        mockMvc.perform(delete("/api/abs-cd8-loinc/MISSING")).andExpect(status().isNotFound());
    }
}
