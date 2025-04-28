package edu.ctsa.emory.cfar_rid.controller;

import edu.ctsa.emory.cfar_rid.entity.CfarMedsWithDates;
import edu.ctsa.emory.cfar_rid.service.CfarMedsWithDatesService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * REST controller for managing medication records with dates.
 */
@RestController
@RequestMapping("/api/meds-with-dates")
@Slf4j
@Tag(name = "MedsWithDates", description = "Endpoints for managing medications with start/end dates")
public class CfarMedsWithDatesController {

    private final CfarMedsWithDatesService service;

    public CfarMedsWithDatesController(CfarMedsWithDatesService service) {
        this.service = service;
    }

    @Operation(summary = "Get all medication records")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Records retrieved",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CfarMedsWithDates.class)))),
            @ApiResponse(responseCode = "204", description = "No records found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<CfarMedsWithDates> data = service.getAll();
            if (data.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No medication records found.");
            }
            return ResponseEntity.ok(data);
        } catch (Exception ex) {
            log.error("Error retrieving records", ex);
            return internalError("Failed to retrieve medication records.");
        }
    }

    @Operation(summary = "Get medication record by Study ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Record found",
                    content = @Content(schema = @Schema(implementation = CfarMedsWithDates.class))),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping("/{studyId}")
    public ResponseEntity<?> getById(@PathVariable String studyId) {
        try {
            Optional<CfarMedsWithDates> record = service.getById(studyId);
            if (record.isPresent()) return ResponseEntity.ok(record.get());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medication record not found for ID: " + studyId);
        } catch (Exception ex) {
            log.error("Error retrieving medication record", ex);
            return internalError("Failed to retrieve medication record.");
        }
    }

    @Operation(summary = "Create or update a medication record")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Record saved",
                    content = @Content(schema = @Schema(implementation = CfarMedsWithDates.class))),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CfarMedsWithDates meds) {
        try {
            return ResponseEntity.ok(service.save(meds));
        } catch (Exception ex) {
            log.error("Error saving medication record", ex);
            return internalError("Failed to save medication record.");
        }
    }

    @Operation(summary = "Delete medication record by Study ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Record deleted"),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @DeleteMapping("/{studyId}")
    public ResponseEntity<?> delete(@PathVariable String studyId) {
        try {
            boolean deleted = service.deleteById(studyId);
            if (deleted) return ResponseEntity.noContent().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medication record not found for ID: " + studyId);
        } catch (Exception ex) {
            log.error("Error deleting medication record", ex);
            return internalError("Failed to delete medication record.");
        }
    }

    private ResponseEntity<Map<String, Object>> internalError(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "timestamp", new Date(),
                "status", 500,
                "error", "Internal Server Error",
                "message", message
        ));
    }
}
