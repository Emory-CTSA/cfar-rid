package edu.ctsa.emory.cfar_rid.controller;

import edu.ctsa.emory.cfar_rid.entity.CfarArvStartDate;
import edu.ctsa.emory.cfar_rid.service.CfarArvStartDateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * REST controller for managing ARV start date data.
 */
@RestController
@RequestMapping("/api/arv-start")
@Slf4j
@Tag(name = "ARV Start Date", description = "Endpoints for managing ARV start dates")
public class CfarArvStartDateController {

    private final CfarArvStartDateService service;

    public CfarArvStartDateController(CfarArvStartDateService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all ARV start records")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved records",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CfarArvStartDate.class)))),
            @ApiResponse(responseCode = "204", description = "No records found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> getAll() {
        try {
            List<CfarArvStartDate> records = service.getAll();
            return records.isEmpty()
                    ? ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data found.")
                    : ResponseEntity.ok(records);
        } catch (Exception e) {
            log.error("Error fetching ARV start records", e);
            return buildErrorResponse("Failed to fetch ARV start records");
        }
    }

    @GetMapping("/{studyId}")
    @Operation(summary = "Get ARV start record by Study ID", description = "Returns a single ARV start record by study ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved record",
                    content = @Content(schema = @Schema(implementation = CfarArvStartDate.class))),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> getById(@PathVariable String studyId) {
        try {
            log.info("Fetching ARV start date record by ID: {}", studyId);
            Optional<CfarArvStartDate> result = service.getById(studyId);

            if (result.isPresent()) {
                return ResponseEntity.ok(result.get());
            } else {
                log.warn("ARV record not found for ID: {}", studyId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("ARV start record not found for ID: " + studyId);
            }
        } catch (Exception e) {
            log.error("Error fetching ARV start record by ID: {}", studyId, e);
            return buildErrorResponse("An error occurred while retrieving the ARV start record");
        }
    }

    @PostMapping
    @Operation(summary = "Save or update ARV start record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully saved record",
                    content = @Content(schema = @Schema(implementation = CfarArvStartDate.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> save(@RequestBody CfarArvStartDate entity) {
        try {
            return ResponseEntity.ok(service.save(entity));
        } catch (Exception e) {
            log.error("Error saving ARV start record", e);
            return buildErrorResponse("Error saving ARV start record");
        }
    }

    @DeleteMapping("/{studyId}")
    @Operation(summary = "Delete ARV start record by Study ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted record"),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> delete(@PathVariable String studyId) {
        try {
            boolean deleted = service.deleteById(studyId);
            return deleted
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found for ID: " + studyId);
        } catch (Exception e) {
            log.error("Error deleting ARV start record", e);
            return buildErrorResponse("Error deleting ARV start record");
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
