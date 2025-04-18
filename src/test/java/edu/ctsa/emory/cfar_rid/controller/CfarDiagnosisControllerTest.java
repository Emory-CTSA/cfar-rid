package edu.ctsa.emory.cfar_rid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctsa.emory.cfar_rid.entity.CfarDiagnosis;
import edu.ctsa.emory.cfar_rid.service.CfarDiagnosisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CfarDiagnosisController.class)
class CfarDiagnosisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CfarDiagnosisService service;

    @Autowired
    private ObjectMapper objectMapper;

    private CfarDiagnosis sample;

    @BeforeEach
    void setUp() {
        sample = new CfarDiagnosis();
        sample.setStudyId("STUDY001");
        sample.setDiagnosisName("Tuberculosis");
    }

    @Test
    void testGetAll() throws Exception {
        when(service.getAll()).thenReturn(List.of(sample));

        mockMvc.perform(get("/api/diagnosis"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studyId").value("STUDY001"));
    }

    @Test
    void testGetByIdFound() throws Exception {
        when(service.getById("STUDY001")).thenReturn(Optional.of(sample));

        mockMvc.perform(get("/api/diagnosis/STUDY001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.diagnosisName").value("Tuberculosis"));
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(service.getById("NOT_FOUND")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/diagnosis/NOT_FOUND"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSave() throws Exception {
        when(service.save(any())).thenReturn(sample);

        mockMvc.perform(post("/api/diagnosis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sample)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("STUDY001"));
    }

    @Test
    void testDeleteSuccess() throws Exception {
        when(service.deleteById("STUDY001")).thenReturn(true);

        mockMvc.perform(delete("/api/diagnosis/STUDY001"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteNotFound() throws Exception {
        when(service.deleteById("MISSING")).thenReturn(false);

        mockMvc.perform(delete("/api/diagnosis/MISSING"))
                .andExpect(status().isNotFound());
    }
}
