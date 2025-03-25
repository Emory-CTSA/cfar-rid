package edu.ctsa.emory.cfar_rid.service;

import edu.ctsa.emory.cfar_rid.dto.CohortCd4DTO;
import edu.ctsa.emory.cfar_rid.repository.CustomDataRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomDataServiceTest {

    @Mock
    private CustomDataRepository repository;

    @InjectMocks
    private CustomDataService service;

    public CustomDataServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCohortSiteCd4Data() {
        Object[] row1 = new Object[]{"ATLANTA", "Site-001", "425"};
        Object[] row2 = new Object[]{"MIAMI", "Site-002", "500"};

        when(repository.fetchCohortSiteCd4Raw()).thenReturn(Arrays.asList(row1, row2));

        List<CohortCd4DTO> result = service.getCohortSiteCd4Data();

        assertEquals(2, result.size());
        assertEquals("ATLANTA", result.get(0).getCohort());
        assertEquals("Site-001", result.get(0).getSite());
        assertEquals("425", result.get(0).getCd4Count());
    }
}
