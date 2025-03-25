package edu.ctsa.emory.cfar_rid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctsa.emory.cfar_rid.dto.CohortCd4DTO;
import edu.ctsa.emory.cfar_rid.service.CustomDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomDataController.class)
@AutoConfigureMockMvc(addFilters = false)
class CustomDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomDataService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetCohortSiteCd4() throws Exception {
        CohortCd4DTO dto1 = new CohortCd4DTO("ATLANTA", "Site-001", "425");
        CohortCd4DTO dto2 = new CohortCd4DTO("MIAMI", "Site-002", "500");

        when(service.getCohortSiteCd4Data()).thenReturn(Arrays.asList(dto1, dto2));

        mockMvc.perform(get("/api/data/cohort-cd4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].cohort").value("ATLANTA"))
                .andExpect(jsonPath("$[0].cd4Count").value("425"));
    }

    @Test
    void testGetCohortSiteCd4_NoContent() throws Exception {
        when(service.getCohortSiteCd4Data()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/data/cohort-cd4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
