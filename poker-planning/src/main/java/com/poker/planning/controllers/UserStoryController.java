package com.poker.planning.controllers;

import com.poker.planning.converters.UserStoryConverter;
import com.poker.planning.dto.UserStoryDTO;
import com.poker.planning.dto.api.dto.UserStoryApiDTO;
import com.poker.planning.entities.UserStory;
import com.poker.planning.services.PokerPlanningSessionService;
import com.poker.planning.services.UserStoryService;
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
@Tag(name = "User Story", description = "This controller provides APIs for User Stories")
public class UserStoryController {
    @Autowired
    private UserStoryService userStoryService;
    @Autowired
    private PokerPlanningSessionService pokerPlanningSessionService;

    @Operation(summary = "List of user stories")
    @GetMapping("/stories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of user stories",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserStoryApiDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)
    })
    public ResponseEntity<List<UserStoryApiDTO>> getUserStories() {
        List<UserStory> stories = userStoryService.getUserStories();
        if (!stories.isEmpty()) {
            List<UserStoryApiDTO>storyDTOList= stories.stream().map(UserStoryConverter::toDTO).collect(Collectors.toList());
            return ResponseEntity.ok(storyDTOList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @Operation(summary = "Creation of a user story")
    @PostMapping("/{sessionId}/stories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created JSON object",
                    content =@Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)
    })
    public ResponseEntity<String> createUserStory(@PathVariable Long sessionId, @RequestBody UserStoryDTO userStoryDTO) {

        try {
            UserStory userStory=UserStoryConverter.toEntity(userStoryDTO);
            userStoryService.createUserStory(sessionId, userStory);
            return ResponseEntity.status(HttpStatus.CREATED).body("User story created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Update story information")
    @PutMapping("/{sessionId}/stories/{userStoryId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated JSON object",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)
    })
    public ResponseEntity<String> updateUserStory(@PathVariable Long sessionId, @PathVariable Long userStoryId, @RequestBody UserStoryDTO userStoryDTO) {

        try {
            UserStory userStory=UserStoryConverter.toEntity(userStoryDTO);
            userStoryService.updateUserStory(sessionId, userStoryId, userStory);
            return ResponseEntity.status(HttpStatus.OK).body("User story updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete user story")
    @DeleteMapping("/{sessionId}/stories/{userStoryId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted JSON object",
                    content =@Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)
    })
    public ResponseEntity<String> deleteUserStory(@PathVariable Long sessionId, @PathVariable Long userStoryId) {

        try {
            userStoryService.deleteUserStory(sessionId, userStoryId);
            return ResponseEntity.ok("User story deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
