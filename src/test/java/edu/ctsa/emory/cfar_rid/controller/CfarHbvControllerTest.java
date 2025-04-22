package edu.ctsa.emory.cfar_rid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctsa.emory.cfar_rid.entity.CfarHbv;
import edu.ctsa.emory.cfar_rid.service.CfarHbvService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CfarHbvController.class)
class CfarHbvControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CfarHbvService service;

    @Autowired
    private ObjectMapper objectMapper;

    private CfarHbv mockHbv;

    @BeforeEach
    void setUp() {
        mockHbv = CfarHbv.builder()
                .studyId("123")
                .personKey("person123")
                .description("HBV Desc")
                .lab("LabCorp")
                .result("Positive")
                .labDate(new Date())
                .daysFromHivDxToLab(20)
                .loinc("12345-6")
                .build();
    }

    @Test
    void testGetAllHbvRecords() throws Exception {
        when(service.getAll()).thenReturn(List.of(mockHbv));

        mockMvc.perform(get("/api/hbv"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studyId").value("123"));
    }

    @Test
    void testGetHbvByIdFound() throws Exception {
        when(service.getById("123")).thenReturn(Optional.of(mockHbv));

        mockMvc.perform(get("/api/hbv/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("123"));
    }

    @Test
    void testGetHbvByIdNotFound() throws Exception {
        when(service.getById("999")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/hbv/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSaveHbv() throws Exception {
        when(service.save(any(CfarHbv.class))).thenReturn(mockHbv);

        mockMvc.perform(post("/api/hbv")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockHbv)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("123"));
    }

    @Test
    void testDeleteHbvSuccess() throws Exception {
        when(service.deleteById("123")).thenReturn(true);

        mockMvc.perform(delete("/api/hbv/123"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteHbvNotFound() throws Exception {
        when(service.deleteById("999")).thenReturn(false);

        mockMvc.perform(delete("/api/hbv/999"))
                .andExpect(status().isNotFound());
    }
}
