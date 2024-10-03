package com.poker.planning.controllers;

import com.poker.planning.converters.PokerPlanningSessionConverter;
import com.poker.planning.dto.PokerPlanningSessionDTO;
import com.poker.planning.entities.PokerPlanningSession;
import com.poker.planning.services.PokerPlanningSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@Tag(name = "PokerPlanningSession", description = "This controller provides APIs for Poker planning sessions")
public class PokerPlanningSessionController {
    @Autowired
    private PokerPlanningSessionService pokerPlanningSessionService;

    @Operation(summary = "Get Sessions", description = "Fetches all available sessions")
    @GetMapping("/sessions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched session data",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PokerPlanningSessionDTO.class))
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    public ResponseEntity<List<PokerPlanningSessionDTO>> getSessions() {
        if (!pokerPlanningSessionService.getSessions().isEmpty()) {
            List<PokerPlanningSessionDTO> sessionDTOList = pokerPlanningSessionService.getSessions()
                    .stream().map(PokerPlanningSessionConverter::toDTO).collect(Collectors.toList());
            return ResponseEntity.ok(sessionDTOList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @Operation(summary = "Create Session", description = "Creates a new session")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the session",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    public ResponseEntity<String> createSession(@RequestBody PokerPlanningSessionDTO sessionDTO) {
        try {
            PokerPlanningSession pokerPlanningSession=PokerPlanningSessionConverter.toEntity(sessionDTO);
            pokerPlanningSessionService.createSession(pokerPlanningSession);
            return ResponseEntity.status(HttpStatus.CREATED).body("successfully created the session");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get Session by ID", description = "Fetches a session by its ID")
    @GetMapping("/{sessionId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched session data",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PokerPlanningSessionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Session not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    public ResponseEntity<?> getSessionById(@PathVariable Long sessionId) {

        if (pokerPlanningSessionService.getSessionById(sessionId) != null) {
            PokerPlanningSessionDTO pokerPlanningSessionDTO=PokerPlanningSessionConverter.toDTO(pokerPlanningSessionService.getSessionById(sessionId));
            return ResponseEntity.ok(pokerPlanningSessionDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Session not found..");
    }

    @Operation(summary = "Delete Session by ID", description = "Deletes a session by its ID")
    @DeleteMapping("/{sessionId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the session",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "404", description = "Session not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    public ResponseEntity<String> deleteSessionById(@PathVariable Long sessionId) {

        try {
            pokerPlanningSessionService.deleteSessionById(sessionId);
            return ResponseEntity.ok("Session with ID : " + sessionId + " is deleted successfully..");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
