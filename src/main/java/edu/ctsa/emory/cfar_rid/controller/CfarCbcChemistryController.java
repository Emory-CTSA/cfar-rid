package edu.ctsa.emory.cfar_rid.controller;

import edu.ctsa.emory.cfar_rid.entity.CfarCbcChemistry;
import edu.ctsa.emory.cfar_rid.service.CfarCbcChemistryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * REST controller for managing CBC Chemistry data.
 */
@RestController
@RequestMapping("/api/cbc-chemistry")
@Slf4j
@Tag(name = "CBC Chemistry", description = "Endpoints for managing CBC chemistry lab results")
public class CfarCbcChemistryController {

    private final CfarCbcChemistryService service;

    public CfarCbcChemistryController(CfarCbcChemistryService service) {
        this.service = service;
    }

    /**
     * Retrieves all CBC chemistry records.
     */
    @GetMapping
    @Operation(summary = "Get all CBC Chemistry records")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved records",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CfarCbcChemistry.class)))),
            @ApiResponse(responseCode = "204", description = "No records found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> getAll() {
        try {
            List<CfarCbcChemistry> records = service.getAll();
            return records.isEmpty()
                    ? ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data found.")
                    : ResponseEntity.ok(records);
        } catch (Exception e) {
            log.error("Error fetching CBC chemistry records", e);
            return buildErrorResponse("Error fetching CBC chemistry records");
        }
    }

    /**
     * Retrieves a CBC chemistry record by study ID.
     */
    @GetMapping("/{studyId}")
    @Operation(summary = "Get CBC Chemistry record by Study ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved record",
                    content = @Content(schema = @Schema(implementation = CfarCbcChemistry.class))),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> getById(@PathVariable String studyId) {
        try {
            log.info("Fetching CBC chemistry record by ID: {}", studyId);
            Optional<CfarCbcChemistry> result = service.getById(studyId);

            if (result.isPresent()) {
                return ResponseEntity.ok(result.get());
            } else {
                log.warn("CBC chemistry record not found for ID: {}", studyId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("CBC chemistry record not found for ID: " + studyId);
            }
        } catch (Exception e) {
            log.error("Error fetching CBC chemistry record by ID: {}", studyId, e);
            return buildErrorResponse("An error occurred while retrieving the CBC chemistry record");
        }
    }

    /**
     * Saves or updates a CBC chemistry record.
     */
    @PostMapping
    @Operation(summary = "Save or update CBC Chemistry record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully saved record",
                    content = @Content(schema = @Schema(implementation = CfarCbcChemistry.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> save(@RequestBody CfarCbcChemistry entity) {
        try {
            return ResponseEntity.ok(service.save(entity));
        } catch (Exception e) {
            log.error("Error saving CBC chemistry record", e);
            return buildErrorResponse("An error occurred while saving the CBC chemistry record");
        }
    }

    /**
     * Deletes a CBC chemistry record by study ID.
     */
    @DeleteMapping("/{studyId}")
    @Operation(summary = "Delete CBC Chemistry record by Study ID")
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
                    : ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("CBC chemistry record not found for ID: " + studyId);
        } catch (Exception e) {
            log.error("Error deleting CBC chemistry record", e);
            return buildErrorResponse("Error deleting CBC chemistry record");
        }
    }

    /**
     * Helper method for building consistent error responses.
     */
    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", new Date());
        error.put("status", 500);
        error.put("error", message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
