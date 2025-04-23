package edu.ctsa.emory.cfar_rid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctsa.emory.cfar_rid.entity.CfarHcv;
import edu.ctsa.emory.cfar_rid.service.CfarHcvService;
import org.junit.jupiter.api.*;
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

@WebMvcTest(CfarHcvController.class)
class CfarHcvControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CfarHcvService service;

    @Autowired
    private ObjectMapper objectMapper;

    private CfarHcv hcv;

    @BeforeEach
    void setUp() {
        hcv = new CfarHcv("123", "key123", "desc", "lab", "result", new Date(), 10, "loinc123");
    }

    @Test
    void getAllReturnsOk() throws Exception {
        when(service.getAll()).thenReturn(List.of(hcv));

        mockMvc.perform(get("/api/hcv"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studyId").value("123"));
    }

    @Test
    void getByIdReturnsEntity() throws Exception {
        when(service.getById("123")).thenReturn(Optional.of(hcv));

        mockMvc.perform(get("/api/hcv/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("123"));
    }

    @Test
    void getByIdReturnsNotFound() throws Exception {
        when(service.getById("999")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/hcv/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveReturnsOk() throws Exception {
        when(service.save(any(CfarHcv.class))).thenReturn(hcv);

        mockMvc.perform(post("/api/hcv")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hcv)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("123"));
    }

    @Test
    void deleteReturnsNoContent() throws Exception {
        when(service.deleteById("123")).thenReturn(true);

        mockMvc.perform(delete("/api/hcv/123"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteReturnsNotFound() throws Exception {
        when(service.deleteById("999")).thenReturn(false);

        mockMvc.perform(delete("/api/hcv/999"))
                .andExpect(status().isNotFound());
    }
}
