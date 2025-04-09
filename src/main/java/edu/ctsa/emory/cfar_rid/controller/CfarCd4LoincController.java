package edu.ctsa.emory.cfar_rid.controller;

import edu.ctsa.emory.cfar_rid.entity.CfarCd4Loinc;
import edu.ctsa.emory.cfar_rid.service.CfarCd4LoincService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controller for CD4 LOINC operations.
 */
@RestController
@RequestMapping("/api/cd4-loinc")
@Slf4j
@Tag(name = "CD4 LOINC", description = "Endpoints for managing CD4 lab data")
public class CfarCd4LoincController {

    private final CfarCd4LoincService service;

    public CfarCd4LoincController(CfarCd4LoincService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all CD4 LOINC records")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CfarCd4Loinc.class)))),
            @ApiResponse(responseCode = "204", description = "No records found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> getAll() {
        try {
            List<CfarCd4Loinc> list = service.getAll();
            return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
        } catch (Exception e) {
            log.error("Error retrieving CD4 LOINC records", e);
            return buildErrorResponse("Failed to retrieve CD4 LOINC records");
        }
    }

    @GetMapping("/{studyId}")
    @Operation(summary = "Get CD4 LOINC by Study ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Record found",
                    content = @Content(schema = @Schema(implementation = CfarCd4Loinc.class))),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> getById(@PathVariable String studyId) {
        try {
            log.info("Fetching CD4 LOINC record by ID: {}", studyId);
            Optional<CfarCd4Loinc> result = service.getById(studyId);
            if (result.isPresent()) {
                return ResponseEntity.ok(result.get());
            } else {
                log.warn("CD4 record not found for ID: {}", studyId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CD4 record not found for ID: " + studyId);
            }
        } catch (Exception e) {
            log.error("Error fetching CD4 record by ID: {}", studyId, e);
            return buildErrorResponse("An error occurred while retrieving the CD4 record");
        }
    }

    @PostMapping
    @Operation(summary = "Save or update CD4 LOINC record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully saved record",
                    content = @Content(schema = @Schema(implementation = CfarCd4Loinc.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> save(@RequestBody CfarCd4Loinc entity) {
        try {
            return ResponseEntity.ok(service.save(entity));
        } catch (Exception e) {
            log.error("Error saving CD4 LOINC record", e);
            return buildErrorResponse("Failed to save CD4 LOINC record");
        }
    }

    @DeleteMapping("/{studyId}")
    @Operation(summary = "Delete CD4 LOINC record by Study ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted record"),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> delete(@PathVariable String studyId) {
        try {
            boolean deleted = service.deleteById(studyId);
            return deleted ? ResponseEntity.noContent().build() :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("CD4 record not found for ID: " + studyId);
        } catch (Exception e) {
            log.error("Error deleting CD4 LOINC record", e);
            return buildErrorResponse("Error deleting CD4 LOINC record");
        }
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", new Date());
        error.put("status", 500);
        error.put("error", message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
