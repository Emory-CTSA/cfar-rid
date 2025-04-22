package edu.ctsa.emory.cfar_rid.controller;

import edu.ctsa.emory.cfar_rid.entity.CfarHbv;
import edu.ctsa.emory.cfar_rid.service.CfarHbvService;
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
 * REST controller for managing CFAR HBV data.
 */
@RestController
@RequestMapping("/api/hbv")
@Slf4j
@Tag(name = "HBV", description = "Endpoints for managing CFAR Hepatitis B Virus (HBV) data")
public class CfarHbvController {

    private final CfarHbvService service;

    public CfarHbvController(CfarHbvService service) {
        this.service = service;
    }

    @Operation(summary = "Get all CFAR HBV records", description = "Returns a list of all HBV lab records.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved HBV records",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CfarHbv.class)))),
            @ApiResponse(responseCode = "204", description = "No content found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<?> getAllHbvRecords() {
        try {
            log.info("Calling service to get all HBV records...");
            List<CfarHbv> data = service.getAll();

            if (data == null || data.isEmpty()) {
                log.warn("No HBV records found.");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No HBV records found.");
            }

            log.debug("HBV records retrieved: {}", data.size());
            return ResponseEntity.ok(data);

        } catch (Exception ex) {
            log.error("Error while fetching HBV records", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(
                    "An error occurred while retrieving HBV records"
            ));
        }
    }

    @Operation(summary = "Get HBV record by Study ID", description = "Returns an HBV record by study ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved HBV record",
                    content = @Content(schema = @Schema(implementation = CfarHbv.class))),
            @ApiResponse(responseCode = "404", description = "HBV record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{studyId}")
    public ResponseEntity<?> getHbvById(@PathVariable String studyId) {
        try {
            log.info("Fetching HBV record for ID: {}", studyId);
            Optional<CfarHbv> result = service.getById(studyId);

            if (result.isPresent()) {
                return ResponseEntity.ok(result.get());
            } else {
                log.warn("HBV record not found for ID: {}", studyId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("HBV record not found for ID: " + studyId);
            }
        } catch (Exception ex) {
            log.error("Error fetching HBV record by ID: {}", studyId, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(
                    "An error occurred while retrieving the HBV record"
            ));
        }
    }

    @Operation(summary = "Create or update HBV record", description = "Creates or updates an HBV record.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully saved HBV record",
                    content = @Content(schema = @Schema(implementation = CfarHbv.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> saveHbv(@RequestBody CfarHbv cfarHbv) {
        try {
            log.info("Saving HBV record for ID: {}", cfarHbv.getStudyId());
            CfarHbv saved = service.save(cfarHbv);
            return ResponseEntity.ok(saved);
        } catch (Exception ex) {
            log.error("Error saving HBV record", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(
                    "An error occurred while saving the HBV record"
            ));
        }
    }

    @Operation(summary = "Delete HBV record by Study ID", description = "Deletes an HBV record by its study ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted HBV record"),
            @ApiResponse(responseCode = "404", description = "HBV record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{studyId}")
    public ResponseEntity<?> deleteHbv(@PathVariable String studyId) {
        try {
            log.info("Attempting to delete HBV record with ID: {}", studyId);
            boolean deleted = service.deleteById(studyId);

            if (!deleted) {
                log.warn("HBV record not found for deletion with ID: {}", studyId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("HBV record not found for ID: " + studyId);
            }

            log.debug("Successfully deleted HBV record with ID: {}", studyId);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            log.error("Error deleting HBV record with ID: {}", studyId, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(
                    "An error occurred while deleting the HBV record"
            ));
        }
    }

    /**
     * Utility method to build consistent error response.
     */
    private Map<String, Object> buildErrorResponse(String message) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", new Date());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "Internal Server Error");
        error.put("message", message);
        return error;
    }
}
