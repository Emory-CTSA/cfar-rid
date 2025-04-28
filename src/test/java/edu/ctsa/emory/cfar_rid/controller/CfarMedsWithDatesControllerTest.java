package edu.ctsa.emory.cfar_rid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctsa.emory.cfar_rid.entity.CfarMedsWithDates;
import edu.ctsa.emory.cfar_rid.service.CfarMedsWithDatesService;
import org.junit.jupiter.api.*;
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

@WebMvcTest(CfarMedsWithDatesController.class)
class CfarMedsWithDatesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CfarMedsWithDatesService service;

    @Autowired
    private ObjectMapper objectMapper;

    private CfarMedsWithDates record;

    @BeforeEach
    void setUp() {
        record = CfarMedsWithDates.builder()
                .studyId("MED001")
                .personKey("P123")
                .drugDescription("Abacavir")
                .startDate(new Date())
                .endDate(new Date())
                .rxnorm(123456)
                .medicationClass("NRTI")
                .medDurationDays(30)
                .build();
    }

    @Test
    void testGetAllReturnsData() throws Exception {
        when(service.getAll()).thenReturn(List.of(record));
        mockMvc.perform(get("/api/meds-with-dates"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studyId").value("MED001"));
    }

    @Test
    void testGetAllNoData() throws Exception {
        when(service.getAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/meds-with-dates"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetByIdFound() throws Exception {
        when(service.getById("MED001")).thenReturn(Optional.of(record));
        mockMvc.perform(get("/api/meds-with-dates/MED001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("MED001"));
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(service.getById("MED999")).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/meds-with-dates/MED999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSaveSuccess() throws Exception {
        when(service.save(any(CfarMedsWithDates.class))).thenReturn(record);

        mockMvc.perform(post("/api/meds-with-dates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(record)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studyId").value("MED001"));
    }

    @Test
    void testDeleteSuccess() throws Exception {
        when(service.deleteById("MED001")).thenReturn(true);
        mockMvc.perform(delete("/api/meds-with-dates/MED001"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteNotFound() throws Exception {
        when(service.deleteById("MED999")).thenReturn(false);
        mockMvc.perform(delete("/api/meds-with-dates/MED999"))
                .andExpect(status().isNotFound());
    }
}
