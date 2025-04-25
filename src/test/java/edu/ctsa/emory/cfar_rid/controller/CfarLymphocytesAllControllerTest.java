package edu.ctsa.emory.cfar_rid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctsa.emory.cfar_rid.entity.CfarLymphocytesAll;
import edu.ctsa.emory.cfar_rid.service.CfarLymphocytesAllService;
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

@WebMvcTest(CfarLymphocytesAllController.class)
class CfarLymphocytesAllControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CfarLymphocytesAllService service;

    @Autowired
    private ObjectMapper objectMapper;

    private CfarLymphocytesAll record;

    @BeforeEach
    void setUp() {
        record = CfarLymphocytesAll.builder()
                .studyId("LY001")
                .personKey("P001")
                .description("Lymphocyte info")
                .lab("LabCorp")
                .result("Normal")
                .labDate(new Date())
                .loincCode("12345-6")
                .build();
    }

    @Test
    void testGetAllReturnsData() throws Exception {
        when(service.getAll()).thenReturn(List.of(record));
        mockMvc.perform(get("/api/lymphocytes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studyId").value("LY001"));
    }

    @Test
    void testGetAllNoData() throws Exception {
        when(service.getAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/lymphocytes"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetByIdFound() throws Exception {
        when(service.getById("LY001")).thenReturn(Optional.of(record));
        mockMvc.perform(get("/api/lymphocytes/LY001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("LY001"));
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(service.getById("LY999")).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/lymphocytes/LY999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSave() throws Exception {
        when(service.save(any(CfarLymphocytesAll.class))).thenReturn(record);

        mockMvc.perform(post("/api/lymphocytes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(record)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("LY001"));
    }

    @Test
    void testDeleteSuccess() throws Exception {
        when(service.deleteById("LY001")).thenReturn(true);
        mockMvc.perform(delete("/api/lymphocytes/LY001"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteNotFound() throws Exception {
        when(service.deleteById("LY999")).thenReturn(false);
        mockMvc.perform(delete("/api/lymphocytes/LY999"))
                .andExpect(status().isNotFound());
    }
}
