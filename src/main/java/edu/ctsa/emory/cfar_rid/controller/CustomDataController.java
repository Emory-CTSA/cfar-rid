package edu.ctsa.emory.cfar_rid.controller;

import edu.ctsa.emory.cfar_rid.dto.CohortCd4DTO;
import edu.ctsa.emory.cfar_rid.service.CustomDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;

/**
 * REST controller for exposing custom data endpoints.
 */
@RestController
@RequestMapping("/api/data")
@Slf4j
@Tag(name = "Custom Data", description = "Endpoints for custom cohort data reporting")
public class CustomDataController {

    private final CustomDataService service;

    public CustomDataController(CustomDataService service) {
        this.service = service;
    }

    /**
     * Fetches CD4 data grouped by cohort and site.
     *
     * @return ResponseEntity with list of CohortCd4DTO or error
     */
    @Operation(
            summary = "Get CD4 data by cohort and site",
            description = "Returns a list of CD4 data aggregated by cohort and site."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved CD4 cohort data",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CohortCd4DTO.class)))),
            @ApiResponse(responseCode = "204", description = "No content available"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/cohort-cd4")
    public ResponseEntity<?> getCohortSiteCd4() {
        try {
            log.info("Calling service to get CD4 data...");
            List<CohortCd4DTO> data = service.getCohortSiteCd4Data();

            if (data == null || data.isEmpty()) {
                log.warn("No CD4 data found.");
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("No CD4 data found for cohort and site.");
            }

            log.debug("CD4 data response size: {}", data.size());
            return ResponseEntity.ok(data);

        } catch (Exception ex) {
            log.error("Error while fetching CD4 data", ex);

            Map<String, Object> error = new HashMap<>();
            error.put("timestamp", new Date());
            error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            error.put("error", "Internal Server Error");
            error.put("message", "An error occurred while retrieving CD4 cohort data");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
