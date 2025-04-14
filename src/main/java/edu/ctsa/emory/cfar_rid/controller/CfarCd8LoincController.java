package edu.ctsa.emory.cfar_rid.controller;

import edu.ctsa.emory.cfar_rid.entity.CfarCd8Loinc;
import edu.ctsa.emory.cfar_rid.service.CfarCd8LoincService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controller for managing CD8 LOINC records.
 */
@RestController
@RequestMapping("/api/cd8-loinc")
@Slf4j
@Tag(name = "CD8 LOINC", description = "Endpoints for CD8 lab results")
public class CfarCd8LoincController {

    private final CfarCd8LoincService service;

    public CfarCd8LoincController(CfarCd8LoincService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all CD8 LOINC records")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Records found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CfarCd8Loinc.class)))),
            @ApiResponse(responseCode = "204", description = "No records found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> getAll() {
        try {
            List<CfarCd8Loinc> records = service.getAll();
            return records.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(records);
        } catch (Exception e) {
            log.error("Error fetching CD8 LOINC records", e);
            return buildErrorResponse("Error fetching CD8 LOINC records");
        }
    }

    @GetMapping("/{studyId}")
    @Operation(summary = "Get CD8 LOINC record by Study ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Record found",
                    content = @Content(schema = @Schema(implementation = CfarCd8Loinc.class))),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> getById(@PathVariable String studyId) {
        try {
            Optional<CfarCd8Loinc> result = service.getById(studyId);
            if (result.isPresent()) {
                return ResponseEntity.ok(result.get());
            } else {
                log.warn("CD8 record not found for ID: {}", studyId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CD8 record not found for ID: " + studyId);
            }
        } catch (Exception e) {
            log.error("Error fetching CD8 record with ID: {}", studyId, e);
            return buildErrorResponse("Error fetching CD8 record");
        }
    }

    @PostMapping
    @Operation(summary = "Save or update a CD8 LOINC record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Record saved",
                    content = @Content(schema = @Schema(implementation = CfarCd8Loinc.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> save(@RequestBody CfarCd8Loinc entity) {
        try {
            return ResponseEntity.ok(service.save(entity));
        } catch (Exception e) {
            log.error("Error saving CD8 record", e);
            return buildErrorResponse("Error saving CD8 record");
        }
    }

    @DeleteMapping("/{studyId}")
    @Operation(summary = "Delete CD8 LOINC record by Study ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> delete(@PathVariable String studyId) {
        try {
            boolean deleted = service.deleteById(studyId);
            return deleted
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("CD8 record not found for ID: " + studyId);
        } catch (Exception e) {
            log.error("Error deleting CD8 record", e);
            return buildErrorResponse("Error deleting CD8 record");
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
