package edu.ctsa.emory.cfar_rid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctsa.emory.cfar_rid.entity.CfarLog10Vl;
import edu.ctsa.emory.cfar_rid.service.CfarLog10VlService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CfarLog10VlController.class)
class CfarLog10VlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CfarLog10VlService service;

    @Autowired
    private ObjectMapper objectMapper;

    private CfarLog10Vl record;

    @BeforeEach
    void setUp() {
        record = CfarLog10Vl.builder()
                .studyId("1001")
                .personKey("P001")
                .description("VL Data")
                .log10VlLabResult("2.3")
                .build();
    }

    @Test
    void testGetAllReturnsData() throws Exception {
        when(service.getAll()).thenReturn(List.of(record));
        mockMvc.perform(get("/api/log10vl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studyId").value("1001"));
    }

    @Test
    void testGetAllNoData() throws Exception {
        when(service.getAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/log10vl"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetByIdFound() throws Exception {
        when(service.getById("1001")).thenReturn(Optional.of(record));
        mockMvc.perform(get("/api/log10vl/1001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("1001"));
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(service.getById("9999")).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/log10vl/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSave() throws Exception {
        when(service.save(any(CfarLog10Vl.class))).thenReturn(record);

        mockMvc.perform(post("/api/log10vl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(record)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("1001"));
    }

    @Test
    void testDeleteSuccess() throws Exception {
        when(service.deleteById("1001")).thenReturn(true);
        mockMvc.perform(delete("/api/log10vl/1001"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteNotFound() throws Exception {
        when(service.deleteById("9999")).thenReturn(false);
        mockMvc.perform(delete("/api/log10vl/9999"))
                .andExpect(status().isNotFound());
    }
}
