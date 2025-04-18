package edu.ctsa.emory.cfar_rid.controller;

import edu.ctsa.emory.cfar_rid.entity.CfarDiagnosis;
import edu.ctsa.emory.cfar_rid.service.CfarDiagnosisService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * REST controller for managing diagnosis data.
 */
@RestController
@RequestMapping("/api/diagnosis")
@Slf4j
@Tag(name = "Diagnosis", description = "Endpoints for diagnosis-related data")
public class CfarDiagnosisController {

    private final CfarDiagnosisService service;

    public CfarDiagnosisController(CfarDiagnosisService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all diagnosis records")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CfarDiagnosis.class)))),
            @ApiResponse(responseCode = "204", description = "No records found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = Map.class))) // Optional
    })
    public ResponseEntity<List<CfarDiagnosis>> getAll() {
        try {
            List<CfarDiagnosis> list = service.getAll();
            return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
        } catch (Exception e) {
            log.error("Error fetching diagnosis records", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/{studyId}")
    @Operation(summary = "Get Diagnosis record by Study ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Record found",
                    content = @Content(schema = @Schema(implementation = CfarDiagnosis.class))),
            @ApiResponse(responseCode = "404", description = "Record not found",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = Map.class))) // Or a custom error object
    })
    public ResponseEntity<?> getById(@PathVariable String studyId) {
        try {
            Optional<CfarDiagnosis> result = service.getById(studyId);
            if (result.isPresent()) {
                return ResponseEntity.ok(result.get());
            } else {
                log.warn("Diagnosis record not found for ID: {}", studyId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Diagnosis record not found for ID: " + studyId);
            }
        } catch (Exception e) {
            log.error("Error fetching diagnosis record with ID: {}", studyId, e);
            return buildErrorResponse("Error fetching diagnosis record");
        }
    }

    @PostMapping
    @Operation(summary = "Save or update diagnosis record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Record saved",
                    content = @Content(schema = @Schema(implementation = CfarDiagnosis.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> save(@RequestBody CfarDiagnosis entity) {
        try {
            return ResponseEntity.ok(service.save(entity));
        } catch (Exception e) {
            log.error("Error saving diagnosis record", e);
            return buildErrorResponse("Failed to save diagnosis record");
        }
    }

    @DeleteMapping("/{studyId}")
    @Operation(summary = "Delete diagnosis record by Study ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> delete(@PathVariable String studyId) {
        try {
            boolean deleted = service.deleteById(studyId);
            return deleted ? ResponseEntity.noContent().build() :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diagnosis record not found for ID: " + studyId);
        } catch (Exception e) {
            log.error("Error deleting diagnosis record", e);
            return buildErrorResponse("Error deleting diagnosis record");
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
