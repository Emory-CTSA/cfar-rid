package edu.ctsa.emory.cfar_rid.controller;

import edu.ctsa.emory.cfar_rid.entity.CfarAbsCd4Loinc;
import edu.ctsa.emory.cfar_rid.service.CfarAbsCd4LoincService;
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
 * Controller for CFAR Absolute CD4 LOINC endpoints.
 */
@RestController
@RequestMapping("/api/abs-cd4-loinc")
@Slf4j
@Tag(name = "Absolute CD4 LOINC", description = "Endpoints for managing Absolute CD4 LOINC data")
public class CfarAbsCd4LoincController {

    private final CfarAbsCd4LoincService service;

    public CfarAbsCd4LoincController(CfarAbsCd4LoincService service) {
        this.service = service;
    }

    @Operation(summary = "Get all CD4 LOINC records")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Records found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CfarAbsCd4Loinc.class)))),
            @ApiResponse(responseCode = "204", description = "No records found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<CfarAbsCd4Loinc> data = service.getAll();
            if (data.isEmpty()) {
                log.warn("No CD4 LOINC data found.");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data found.");
            }
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            log.error("Error fetching all CD4 LOINC records", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(
                    "Error fetching CD4 LOINC records"));
        }
    }

    @Operation(summary = "Get CD4 LOINC record by Study ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Record found",
                    content = @Content(schema = @Schema(implementation = CfarAbsCd4Loinc.class))),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{studyId}")
    public ResponseEntity<?> getById(@PathVariable String studyId) {
        try {
            Optional<CfarAbsCd4Loinc> result = service.getById(studyId);

            if (result.isPresent()) {
                return ResponseEntity.ok(result.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("CD4 LOINC record not found for ID: " + studyId);
            }
        } catch (Exception e) {
            log.error("Error fetching CD4 LOINC record by ID: {}", studyId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(buildErrorResponse("An error occurred while retrieving the record"));
        }
    }

    @Operation(summary = "Save or update CD4 LOINC record")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CfarAbsCd4Loinc entity) {
        try {
            CfarAbsCd4Loinc saved = service.save(entity);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            log.error("Error saving CD4 LOINC record", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(
                    "Error saving CD4 LOINC record"));
        }
    }

    @Operation(summary = "Delete CD4 LOINC record by Study ID")
    @DeleteMapping("/{studyId}")
    public ResponseEntity<?> delete(@PathVariable String studyId) {
        try {
            boolean deleted = service.deleteById(studyId);
            if (!deleted) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found for ID: " + studyId);
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting CD4 LOINC record with ID: {}", studyId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(
                    "Error deleting record"));
        }
    }

    private Map<String, Object> buildErrorResponse(String message) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", new Date());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "Internal Server Error");
        error.put("message", message);
        return error;
    }
}
