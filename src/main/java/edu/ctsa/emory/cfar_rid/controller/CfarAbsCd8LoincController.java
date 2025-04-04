package edu.ctsa.emory.cfar_rid.controller;

import edu.ctsa.emory.cfar_rid.entity.CfarAbsCd8Loinc;
import edu.ctsa.emory.cfar_rid.service.CfarAbsCd8LoincService;
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
 * REST controller for managing CFAR Absolute CD8 LOINC data.
 */
@RestController
@RequestMapping("/api/abs-cd8-loinc")
@Slf4j
@Tag(name = "Absolute CD8 LOINC", description = "Endpoints for managing Absolute CD8 LOINC data")
public class CfarAbsCd8LoincController {

    private final CfarAbsCd8LoincService service;

    public CfarAbsCd8LoincController(CfarAbsCd8LoincService service) {
        this.service = service;
    }

    /**
     * Retrieves all CD8 records.
     */
    @GetMapping
    @Operation(
            summary = "Get all CD8 records",
            description = "Fetches all CFAR Absolute CD8 LOINC records from the database."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved CD8 records",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CfarAbsCd8Loinc.class)))),
            @ApiResponse(responseCode = "204", description = "No records found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> getAll() {
        try {
            log.info("Fetching all CD8 LOINC records...");
            List<CfarAbsCd8Loinc> data = service.getAll();

            if (data.isEmpty()) {
                log.warn("No CD8 LOINC records found.");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data found.");
            }

            log.debug("Fetched {} records", data.size());
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            log.error("Error fetching all CD8 records", e);
            return buildErrorResponse("An error occurred while retrieving CD8 records");
        }
    }

    /**
     * Retrieves a CD8 record by study ID.
     */
    @GetMapping("/{studyId}")
    @Operation(
            summary = "Get CD8 record by Study ID",
            description = "Fetches a specific CFAR Absolute CD8 LOINC record by study ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved record",
                    content = @Content(schema = @Schema(implementation = CfarAbsCd8Loinc.class))),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> getById(@PathVariable String studyId) {
        try {
            log.info("Fetching CD8 LOINC record by ID: {}", studyId);
            Optional<CfarAbsCd8Loinc> result = service.getById(studyId);

            if (result.isPresent()) {
                return ResponseEntity.ok(result.get());
            } else {
                log.warn("CD8 record not found for ID: {}", studyId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("CD8 record not found for ID: " + studyId);
            }
        } catch (Exception e) {
            log.error("Error fetching CD8 record by ID: {}", studyId, e);
            return buildErrorResponse("An error occurred while retrieving the CD8 record");
        }
    }

    /**
     * Saves or updates a CD8 record.
     */
    @PostMapping
    @Operation(
            summary = "Save or update CD8 record",
            description = "Creates or updates a CFAR Absolute CD8 LOINC record."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Record saved successfully",
                    content = @Content(schema = @Schema(implementation = CfarAbsCd8Loinc.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> save(@RequestBody CfarAbsCd8Loinc entity) {
        try {
            log.info("Saving CD8 record with ID: {}", entity.getStudyId());
            CfarAbsCd8Loinc saved = service.save(entity);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            log.error("Error saving CD8 record", e);
            return buildErrorResponse("An error occurred while saving the CD8 record");
        }
    }

    /**
     * Deletes a CD8 record by study ID.
     */
    @DeleteMapping("/{studyId}")
    @Operation(
            summary = "Delete CD8 record by Study ID",
            description = "Deletes a CFAR Absolute CD8 LOINC record using the study ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Record deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> delete(@PathVariable String studyId) {
        try {
            log.info("Attempting to delete CD8 record with ID: {}", studyId);
            boolean deleted = service.deleteById(studyId);

            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                log.warn("CD8 record not found for deletion with ID: {}", studyId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("CD8 record not found for ID: " + studyId);
            }
        } catch (Exception e) {
            log.error("Error deleting CD8 record with ID: {}", studyId, e);
            return buildErrorResponse("An error occurred while deleting the CD8 record");
        }
    }

    /**
     * Helper method to build a standardized error response.
     */
    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", new Date());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "Internal Server Error");
        error.put("message", message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
