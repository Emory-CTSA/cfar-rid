package edu.ctsa.emory.cfar_rid.controller;

import edu.ctsa.emory.cfar_rid.entity.CfarArtAll;
import edu.ctsa.emory.cfar_rid.service.CfarArtAllService;
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
 * REST controller for CFAR ART drug history records.
 */
@RestController
@RequestMapping("/api/art")
@Slf4j
@Tag(name = "ART History", description = "Endpoints for managing ART drug history")
public class CfarArtAllController {

    private final CfarArtAllService service;

    public CfarArtAllController(CfarArtAllService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all ART records")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved ART records",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CfarArtAll.class)))),
            @ApiResponse(responseCode = "204", description = "No ART records found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> getAll() {
        try {
            List<CfarArtAll> data = service.getAll();
            if (data.isEmpty()) {
                log.warn("No ART records found.");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data found.");
            }
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            log.error("Error fetching ART records", e);
            return buildErrorResponse("An error occurred while retrieving ART records");
        }
    }

    @GetMapping("/{studyId}")
    @Operation(summary = "Get ART record by Study ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved record",
                    content = @Content(schema = @Schema(implementation = CfarArtAll.class))),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> getById(@PathVariable String studyId) {
        try {
            Optional<CfarArtAll> result = service.getById(studyId);
            if (result.isPresent()) {
                return ResponseEntity.ok(result.get());
            } else {
                log.warn("ART record not found for ID: {}", studyId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("ART record not found for ID: " + studyId);
            }
        } catch (Exception e) {
            log.error("Error fetching ART record by ID: {}", studyId, e);
            return buildErrorResponse("An error occurred while retrieving the ART record");
        }
    }

    @PostMapping
    @Operation(summary = "Create or update ART record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully saved record",
                    content = @Content(schema = @Schema(implementation = CfarArtAll.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> save(@RequestBody CfarArtAll entity) {
        try {
            CfarArtAll saved = service.save(entity);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            log.error("Error saving ART record", e);
            return buildErrorResponse("An error occurred while saving the ART record");
        }
    }

    @DeleteMapping("/{studyId}")
    @Operation(summary = "Delete ART record by Study ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted record"),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> delete(@PathVariable String studyId) {
        try {
            boolean deleted = service.deleteById(studyId);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                log.warn("ART record not found for deletion with ID: {}", studyId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("ART record not found for ID: " + studyId);
            }
        } catch (Exception e) {
            log.error("Error deleting ART record with ID: {}", studyId, e);
            return buildErrorResponse("An error occurred while deleting the ART record");
        }
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", new Date());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "Internal Server Error");
        error.put("message", message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
