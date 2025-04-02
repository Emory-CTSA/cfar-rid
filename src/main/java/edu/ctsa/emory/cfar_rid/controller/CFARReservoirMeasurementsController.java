package edu.ctsa.emory.cfar_rid.controller;

import edu.ctsa.emory.cfar_rid.entity.CFARReservoirMeasurements;
import edu.ctsa.emory.cfar_rid.service.CFARReservoirMeasurementsService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * REST controller for managing CFAR Reservoir Measurements.
 */
@RestController
@RequestMapping("/api/reservoir-measurements")
@Slf4j
@Tag(name = "Reservoir Measurements", description = "Endpoints for managing HIV reservoir measurements")
public class CFARReservoirMeasurementsController {

    private final CFARReservoirMeasurementsService service;

    public CFARReservoirMeasurementsController(CFARReservoirMeasurementsService service) {
        this.service = service;
    }

    @Operation(summary = "Get all reservoir measurement records")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Records retrieved",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CFARReservoirMeasurements.class)))),
            @ApiResponse(responseCode = "204", description = "No records found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<CFARReservoirMeasurements> data = service.getAll();
            if (data.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No reservoir measurement records found.");
            }
            return ResponseEntity.ok(data);
        } catch (Exception ex) {
            log.error("Error fetching records", ex);
            return internalError("Unable to retrieve reservoir measurement records.");
        }
    }

    @Operation(summary = "Get record by person key")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Record found",
                    content = @Content(schema = @Schema(implementation = CFARReservoirMeasurements.class))),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping("/{personKey}")
    public ResponseEntity<?> getById(@PathVariable String personKey) {
        try {
            Optional<CFARReservoirMeasurements> record = service.getById(personKey);
            if (record.isPresent()) return ResponseEntity.ok(record.get());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found for personKey: " + personKey);
        } catch (Exception ex) {
            log.error("Error retrieving record", ex);
            return internalError("Unable to retrieve record.");
        }
    }

    @Operation(summary = "Create or update a reservoir measurement record")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Record saved successfully",
                    content = @Content(schema = @Schema(implementation = CFARReservoirMeasurements.class))),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CFARReservoirMeasurements record) {
        try {
            return ResponseEntity.ok(service.save(record));
        } catch (Exception ex) {
            log.error("Error saving record", ex);
            return internalError("Failed to save reservoir measurement record.");
        }
    }

    @Operation(summary = "Delete record by person key")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Record deleted"),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @DeleteMapping("/{personKey}")
    public ResponseEntity<?> delete(@PathVariable String personKey) {
        try {
            boolean deleted = service.deleteById(personKey);
            if (deleted) return ResponseEntity.noContent().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found for personKey: " + personKey);
        } catch (Exception ex) {
            log.error("Error deleting record", ex);
            return internalError("Failed to delete record.");
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
