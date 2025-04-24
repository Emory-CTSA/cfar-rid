package edu.ctsa.emory.cfar_rid.controller;

import edu.ctsa.emory.cfar_rid.entity.CfarLog10Vl;
import edu.ctsa.emory.cfar_rid.service.CfarLog10VlService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controller for managing Log10 Viral Load (VL) data.
 */
@RestController
@RequestMapping("/api/log10vl")
@Slf4j
@Tag(name = "Log10VL", description = "Endpoints for managing CFAR Log10 Viral Load (VL) data")
public class CfarLog10VlController {

    private final CfarLog10VlService service;

    public CfarLog10VlController(CfarLog10VlService service) {
        this.service = service;
    }

    @Operation(summary = "Get all VL records")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Records retrieved",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CfarLog10Vl.class)))),
            @ApiResponse(responseCode = "204", description = "No records found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<CfarLog10Vl> records = service.getAll();
            if (records.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No VL records found.");
            }
            return ResponseEntity.ok(records);
        } catch (Exception e) {
            log.error("Error fetching VL records", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error("Failed to fetch VL records"));
        }
    }

    @Operation(summary = "Get VL record by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Record found",
                    content = @Content(schema = @Schema(implementation = CfarLog10Vl.class))),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping("/{studyId}")
    public ResponseEntity<?> getById(@PathVariable String studyId) {
        try {
            Optional<CfarLog10Vl> record = service.getById(studyId);
            if (record.isPresent()) return ResponseEntity.ok(record.get());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VL record not found for ID: " + studyId);
        } catch (Exception e) {
            log.error("Error fetching VL record by ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error("Failed to fetch VL record"));
        }
    }

    @Operation(summary = "Create or update VL record")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Record saved",
                    content = @Content(schema = @Schema(implementation = CfarLog10Vl.class))),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CfarLog10Vl vl) {
        try {
            return ResponseEntity.ok(service.save(vl));
        } catch (Exception e) {
            log.error("Error saving VL record", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error("Failed to save VL record"));
        }
    }

    @Operation(summary = "Delete VL record by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Record deleted"),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @DeleteMapping("/{studyId}")
    public ResponseEntity<?> delete(@PathVariable String studyId) {
        try {
            if (service.deleteById(studyId)) return ResponseEntity.noContent().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VL record not found for ID: " + studyId);
        } catch (Exception e) {
            log.error("Error deleting VL record", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error("Failed to delete VL record"));
        }
    }

    private Map<String, Object> error(String msg) {
        return Map.of(
                "timestamp", new Date(),
                "status", 500,
                "error", "Internal Server Error",
                "message", msg
        );
    }
}
