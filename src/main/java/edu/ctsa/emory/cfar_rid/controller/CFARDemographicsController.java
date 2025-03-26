package edu.ctsa.emory.cfar_rid.controller;

import edu.ctsa.emory.cfar_rid.entity.CFARDemographics;
import edu.ctsa.emory.cfar_rid.service.CFARDemographicsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * REST controller for managing CFAR Demographic data.
 */
@RestController
@RequestMapping("/api/demographics")
@Slf4j
@Tag(name = "CFAR Demographics", description = "Endpoints for managing CFAR Demographics")
public class CFARDemographicsController {

    private final CFARDemographicsService service;

    public CFARDemographicsController(CFARDemographicsService service) {
        this.service = service;
    }

    /**
     * Fetches all demographic records.
     * @return list of CFAR Demographics
     */
    @Operation(summary = "Get all CFAR demographic records", description = "Returns a list of all demographics.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved demographics",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CFARDemographics.class)))),
            @ApiResponse(responseCode = "204", description = "No content found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<?> getAllDemographics() {
        try {
            log.info("Calling service to get all demographics...");
            List<CFARDemographics> data = service.getAll();

            if (data == null || data.isEmpty()) {
                log.warn("No demographic records found.");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No demographics found.");
            }

            log.debug("Demographics retrieved: {}", data.size());
            return ResponseEntity.ok(data);

        } catch (Exception ex) {
            log.error("Error while fetching demographics", ex);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(
                    "An error occurred while retrieving demographic records"
            ));
        }
    }

    /**
     * Fetch a demographic record by study ID.
     */
    @Operation(summary = "Get demographic by Study ID", description = "Returns a demographic record by study ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved demographic",
                    content = @Content(schema = @Schema(implementation = CFARDemographics.class))),
            @ApiResponse(responseCode = "404", description = "Demographic not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{studyId}")
    public ResponseEntity<?> getDemographicById(@PathVariable String studyId) {
        try {
            log.info("Fetching demographic for ID: {}", studyId);
            Optional<CFARDemographics> result = service.getById(studyId);

            if (result.isPresent()) {
                return ResponseEntity.ok(result.get());
            } else {
                log.warn("Demographic not found for ID: {}", studyId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Demographic not found for ID: " + studyId);
            }
        } catch (Exception ex) {
            log.error("Error fetching demographic by ID: {}", studyId, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(
                    "An error occurred while retrieving the demographic record"
            ));
        }
    }

    /**
     * Creates or updates a demographic record.
     */
    @Operation(summary = "Create or update demographic", description = "Creates or updates a demographic entry.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully saved demographic",
                    content = @Content(schema = @Schema(implementation = CFARDemographics.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> saveDemographic(@RequestBody CFARDemographics demographic) {
        try {
            log.info("Saving demographic for ID: {}", demographic.getStudyId());
            CFARDemographics saved = service.save(demographic);
            return ResponseEntity.ok(saved);
        } catch (Exception ex) {
            log.error("Error saving demographic", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(
                    "An error occurred while saving the demographic record"
            ));
        }
    }

    /**
     * Deletes a demographic by study ID.
     */
    @Operation(summary = "Delete demographic by Study ID", description = "Deletes a demographic by its study ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted demographic"),
            @ApiResponse(responseCode = "404", description = "Demographic not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{studyId}")
    public ResponseEntity<?> deleteDemographic(@PathVariable String studyId) {
        try {
            log.info("Attempting to delete demographic with ID: {}", studyId);
            boolean deleted = service.deleteById(studyId);

            if (!deleted) {
                log.warn("Demographic not found for deletion with ID: {}", studyId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Demographic not found for ID: " + studyId);
            }

            log.debug("Successfully deleted demographic with ID: {}", studyId);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            log.error("Error deleting demographic with ID: {}", studyId, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(
                    "An error occurred while deleting the demographic record"
            ));
        }
    }

    /**
     * Utility method to build consistent error response.
     */
    private Map<String, Object> buildErrorResponse(String message) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", new Date());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "Internal Server Error");
        error.put("message", message);
        return error;
    }
}
