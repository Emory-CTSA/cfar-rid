package edu.ctsa.emory.cfar_rid.controller;

import edu.ctsa.emory.cfar_rid.entity.CfarHcv;
import edu.ctsa.emory.cfar_rid.service.CfarHcvService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * REST controller for managing CFAR Hepatitis C Virus (HCV) lab data.
 */
@RestController
@RequestMapping("/api/hcv")
@Slf4j
@Tag(name = "HCV", description = "Endpoints for managing CFAR Hepatitis C Virus (HCV) records")
public class CfarHcvController {

    private final CfarHcvService service;

    public CfarHcvController(CfarHcvService service) {
        this.service = service;
    }

    /**
     * Retrieves all HCV records.
     */
    @Operation(summary = "Get all CFAR HCV records", description = "Returns a list of all HCV lab records.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved HCV records",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CfarHcv.class)))),
            @ApiResponse(responseCode = "204", description = "No HCV records found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<CfarHcv> records = service.getAll();
            if (records.isEmpty()) {
                log.warn("No HCV records found.");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No HCV records found.");
            }
            return ResponseEntity.ok(records);
        } catch (Exception ex) {
            log.error("Error fetching HCV records", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(buildErrorResponse("Unable to fetch HCV records"));
        }
    }

    /**
     * Retrieves a specific HCV record by Study ID.
     */
    @Operation(summary = "Get HCV record by Study ID", description = "Returns a single HCV record.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved HCV record",
                    content = @Content(schema = @Schema(implementation = CfarHcv.class))),
            @ApiResponse(responseCode = "404", description = "HCV record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{studyId}")
    public ResponseEntity<?> getById(@PathVariable String studyId) {
        try {
            Optional<CfarHcv> result = service.getById(studyId);
            if (result.isPresent()) {
                return ResponseEntity.ok(result.get());
            } else {
                log.warn("HCV record not found for ID: {}", studyId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("HCV record not found for ID: " + studyId);
            }
        } catch (Exception ex) {
            log.error("Error fetching HCV record with ID: {}", studyId, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(buildErrorResponse("Unable to fetch HCV record"));
        }
    }

    /**
     * Creates or updates an HCV record.
     */
    @Operation(summary = "Create or update an HCV record", description = "Saves an HCV lab record to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HCV record saved successfully",
                    content = @Content(schema = @Schema(implementation = CfarHcv.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CfarHcv hcv) {
        try {
            CfarHcv saved = service.save(hcv);
            return ResponseEntity.ok(saved);
        } catch (Exception ex) {
            log.error("Error saving HCV record", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(buildErrorResponse("Unable to save HCV record"));
        }
    }

    /**
     * Deletes an HCV record by Study ID.
     */
    @Operation(summary = "Delete an HCV record by Study ID", description = "Deletes an HCV record from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "HCV record deleted successfully"),
            @ApiResponse(responseCode = "404", description = "HCV record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{studyId}")
    public ResponseEntity<?> delete(@PathVariable String studyId) {
        try {
            boolean deleted = service.deleteById(studyId);
            if (!deleted) {
                log.warn("HCV record not found for deletion with ID: {}", studyId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("HCV record not found for ID: " + studyId);
            }
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            log.error("Error deleting HCV record", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(buildErrorResponse("Unable to delete HCV record"));
        }
    }

    /**
     * Utility method to return consistent error responses.
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
