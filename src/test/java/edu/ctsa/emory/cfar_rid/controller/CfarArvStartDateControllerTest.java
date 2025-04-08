package edu.ctsa.emory.cfar_rid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctsa.emory.cfar_rid.entity.CfarArvStartDate;
import edu.ctsa.emory.cfar_rid.service.CfarArvStartDateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(CfarArvStartDateController.class)
class CfarArvStartDateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CfarArvStartDateService service;

    @Autowired
    private ObjectMapper objectMapper;

    private CfarArvStartDate sample;

    @BeforeEach
    void setup() {
        sample = new CfarArvStartDate();
        sample.setStudyId("STUDY001");
        sample.setPersonKey("PERSON001");
        sample.setDrugDescription("Test Drug");
    }

    @Test
    void testGetAll() throws Exception {
        when(service.getAll()).thenReturn(List.of(sample));

        mockMvc.perform(get("/api/arv-start"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void testGetByIdFound() throws Exception {
        when(service.getById("STUDY001")).thenReturn(Optional.of(sample));

        mockMvc.perform(get("/api/arv-start/STUDY001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("STUDY001"));
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(service.getById("MISSING")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/arv-start/MISSING"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSave() throws Exception {
        when(service.save(any(CfarArvStartDate.class))).thenReturn(sample);

        mockMvc.perform(post("/api/arv-start")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sample)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("STUDY001"));
    }

    @Test
    void testDeleteSuccess() throws Exception {
        when(service.deleteById("STUDY001")).thenReturn(true);

        mockMvc.perform(delete("/api/arv-start/STUDY001"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteNotFound() throws Exception {
        when(service.deleteById("MISSING")).thenReturn(false);

        mockMvc.perform(delete("/api/arv-start/MISSING"))
                .andExpect(status().isNotFound());
    }
}
