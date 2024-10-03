package com.poker.planning.controllers;

import com.poker.planning.converters.MembersConverter;
import com.poker.planning.dto.MemberDTO;
import com.poker.planning.dto.UserStoryDTO;
import com.poker.planning.entities.Member;
import com.poker.planning.services.MemberService;
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
@Tag(name = "Members", description = "This controller provides APIs for Members")
public class MembersController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private PokerPlanningSessionService pokerPlanningSessionService;

    @Operation(summary = "Join Session", description = "Provides the ability to join the session")
    @PostMapping("/sessions/{sessionId}/members")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successfully joined the session",
                    content =  @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)

    })
    public ResponseEntity<?> joinSession(@PathVariable Long sessionId, @RequestBody MemberDTO memberDTO) {

        try {
            Member member=MembersConverter.toEntity(memberDTO);
            memberService.joinSession(sessionId, member);
            return ResponseEntity.status(HttpStatus.CREATED).body("successfully joined the session");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get members", description = "Fetches all available members")
    @GetMapping("/sessions/{sessionId}/members")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully fetched members data",
                    content =  @Content(mediaType = "application/json", schema = @Schema(implementation = MemberDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)

    })
    public ResponseEntity<?> getMembersInSession(@PathVariable Long sessionId) {

        if (!memberService.getMembersInSession(sessionId).isEmpty()) {
            List<MemberDTO> memberDTO= memberService.getMembersInSession(sessionId).stream().map(MembersConverter::toDTO).collect(Collectors.toList());
           return ResponseEntity.ok(memberDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to get members..");
    }
    @Operation(summary = "Logout a Member")
    @DeleteMapping("/{sessionId}/members/{memberId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logout member",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content)
    })
    public ResponseEntity<String> logoutMember(@PathVariable Long sessionId, @PathVariable Long memberId) {

        try {
            memberService.logoutMember(sessionId, memberId);
            return ResponseEntity.ok("Member logged out successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @Operation(summary = "Update story information")
    @PutMapping("/{sessionId}/members/{memberId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated JSON object",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)
    })
    public ResponseEntity<?> updateMember(@PathVariable Long sessionId, @PathVariable Long memberId, @RequestBody MemberDTO memberDTO){
        try{
            Member member=MembersConverter.toEntity(memberDTO);
            memberService.updateMember(sessionId,memberId,member);
           return ResponseEntity.status(HttpStatus.OK).body("Successfully updated");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

