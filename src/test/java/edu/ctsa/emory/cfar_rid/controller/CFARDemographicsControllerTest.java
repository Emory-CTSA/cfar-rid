package edu.ctsa.emory.cfar_rid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctsa.emory.cfar_rid.entity.CFARDemographics;
import edu.ctsa.emory.cfar_rid.service.CFARDemographicsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
 * Unit tests for CFARDemographicsController
 */
@WebMvcTest(CFARDemographicsController.class)
class CFARDemographicsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CFARDemographicsService service;

    @Autowired
    private ObjectMapper objectMapper;

    private CFARDemographics sample;

    @BeforeEach
    void setup() {
        sample = new CFARDemographics();
        sample.setStudyId("STUDY001");
        sample.setPersonKey("PERSON001");
        sample.setSite("SITE_A");
        sample.setCohort("COHORT_X");
    }

    @Test
    void testGetAllDemographics() throws Exception {
        when(service.getAll()).thenReturn(List.of(sample));

        mockMvc.perform(get("/api/demographics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void testGetDemographicByIdFound() throws Exception {
        when(service.getById("STUDY001")).thenReturn(Optional.of(sample));

        mockMvc.perform(get("/api/demographics/STUDY001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("STUDY001"));
    }

    @Test
    void testGetDemographicByIdNotFound() throws Exception {
        when(service.getById("NOT_FOUND")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/demographics/NOT_FOUND"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSaveDemographic() throws Exception {
        when(service.save(any(CFARDemographics.class))).thenReturn(sample);

        mockMvc.perform(post("/api/demographics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sample)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("STUDY001"));
    }

    @Test
    void testDeleteDemographicSuccess() throws Exception {
        when(service.deleteById("STUDY001")).thenReturn(true);

        mockMvc.perform(delete("/api/demographics/STUDY001"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteDemographicNotFound() throws Exception {
        when(service.deleteById("NOT_FOUND")).thenReturn(false);

        mockMvc.perform(delete("/api/demographics/NOT_FOUND"))
                .andExpect(status().isNotFound());
    }
}
