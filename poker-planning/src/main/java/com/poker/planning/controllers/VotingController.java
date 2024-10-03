package com.poker.planning.controllers;

import com.poker.planning.converters.VotingConverter;
import com.poker.planning.dto.VoteDTO;
import com.poker.planning.dto.api.dto.VoteApiDTO;
import com.poker.planning.entities.UserStory;
import com.poker.planning.entities.Vote;
import com.poker.planning.services.PokerPlanningSessionService;
import com.poker.planning.services.VotesService;
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
@Tag(name = "Voting", description = "This controller provides APIs for Voting")
public class VotingController {
    @Autowired
    private VotesService votesService;
    @Autowired
    private PokerPlanningSessionService pokerPlanningSessionService;
    @Operation(summary = "Start voting")
    @PostMapping("/{sessionId}/{userStoryId}/votes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created JSON object",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)
    })
    public ResponseEntity<String> startVoting(@PathVariable Long sessionId,@PathVariable Long userStoryId) {
        try {
            votesService.startVoting(sessionId, userStoryId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Voting started successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Emit a vote")
    @PostMapping("/{sessionId}/{userStoryId}/votes/{memberId}/{value}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created JSON object",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)
    })
    public ResponseEntity<String> emitVote(@PathVariable Long sessionId, @PathVariable Long userStoryId, @PathVariable Long memberId, @PathVariable String value) {
        try {
            votesService.emitVote(sessionId,userStoryId,memberId, value);
            return ResponseEntity.status(HttpStatus.CREATED).body("Vote emitted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "List of votes emitted")
    @GetMapping("/{sessionId}/votes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of votes",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = VoteDTO.class))),
    })
    public ResponseEntity<?> getVoteList(@PathVariable Long sessionId) {
        List<Vote> votes = votesService.getVoteList(sessionId);
        List<VoteApiDTO> voteDTOList= votes.stream().map(VotingConverter::toDTO).collect(Collectors.toList());
        if (!votes.isEmpty()) {
            return ResponseEntity.ok(voteDTOList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot emit a vote");
    }
    @Operation(summary = "Delete the vote")
    @DeleteMapping("/{sessionId}/votes/{voteId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete the vote by vote ID",
                    content = @Content(mediaType = "text/plain")),
    })
    public ResponseEntity<String> deleteVote(@PathVariable Long voteId){
        votesService.deleteVote(voteId);
        return ResponseEntity.status(HttpStatus.OK).body("Vote deleted successfully with ID :"+voteId);

    }
    @Operation(summary = "Update the vote")
    @PutMapping("/{sessionId}/votes/{voteId}/{userStoryId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Provides the ability to update the vote",
                    content = @Content(mediaType = "text/plain")),
    })
    public ResponseEntity<String> updateVoting(@PathVariable Long voteId,@PathVariable Long userStoryId,@PathVariable Long sessionId, @RequestBody VoteDTO voteDTO){
        try{
            Vote vote=VotingConverter.toEntity(voteDTO);
            votesService.updateVote(voteId,userStoryId,sessionId,vote);
            return ResponseEntity.status(HttpStatus.CREATED).body("Updated successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot update the vote "+e.getMessage());
        }
    }
    @Operation(summary = "Finish voting")
    @PostMapping("/{sessionId}/votes/{userStoryId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created JSON object",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)
    })
    public ResponseEntity<String> finishVoting(@PathVariable Long sessionId, @PathVariable Long userStoryId){
        try {
            votesService.stopVoting(sessionId, userStoryId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Voting finished successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
