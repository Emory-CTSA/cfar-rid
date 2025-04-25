package edu.ctsa.emory.cfar_rid.controller;

import edu.ctsa.emory.cfar_rid.entity.CfarLymphocytesAll;
import edu.ctsa.emory.cfar_rid.service.CfarLymphocytesAllService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * REST controller for managing CFAR Lymphocytes All data.
 */
@RestController
@RequestMapping("/api/lymphocytes")
@Slf4j
@Tag(name = "Lymphocytes", description = "Endpoints for managing lymphocyte lab data")
public class CfarLymphocytesAllController {

    private final CfarLymphocytesAllService service;

    public CfarLymphocytesAllController(CfarLymphocytesAllService service) {
        this.service = service;
    }

    @Operation(summary = "Get all lymphocyte records")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Records retrieved",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CfarLymphocytesAll.class)))),
            @ApiResponse(responseCode = "204", description = "No records found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<CfarLymphocytesAll> records = service.getAll();
            if (records.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No lymphocyte records found.");
            }
            return ResponseEntity.ok(records);
        } catch (Exception ex) {
            log.error("Error retrieving records", ex);
            return internalError("Failed to fetch lymphocyte records");
        }
    }

    @Operation(summary = "Get lymphocyte record by Study ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Record retrieved",
                    content = @Content(schema = @Schema(implementation = CfarLymphocytesAll.class))),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{studyId}")
    public ResponseEntity<?> getById(@PathVariable String studyId) {
        try {
            Optional<CfarLymphocytesAll> record = service.getById(studyId);
            if (record.isPresent()) {
                return ResponseEntity.ok(record.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found for ID: " + studyId);
            }
        } catch (Exception ex) {
            log.error("Error fetching record", ex);
            return internalError("Failed to fetch lymphocyte record");
        }
    }

    @Operation(summary = "Create or update a lymphocyte record")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Record saved successfully",
                    content = @Content(schema = @Schema(implementation = CfarLymphocytesAll.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CfarLymphocytesAll record) {
        try {
            return ResponseEntity.ok(service.save(record));
        } catch (Exception ex) {
            log.error("Error saving record", ex);
            return internalError("Failed to save lymphocyte record");
        }
    }

    @Operation(summary = "Delete lymphocyte record by Study ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Record deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{studyId}")
    public ResponseEntity<?> delete(@PathVariable String studyId) {
        try {
            if (service.deleteById(studyId)) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found for ID: " + studyId);
            }
        } catch (Exception ex) {
            log.error("Error deleting record", ex);
            return internalError("Failed to delete lymphocyte record");
        }
    }

    private ResponseEntity<Map<String, Object>> internalError(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "timestamp", new Date(),
                        "status", 500,
                        "error", "Internal Server Error",
                        "message", message
                ));
    }
}
