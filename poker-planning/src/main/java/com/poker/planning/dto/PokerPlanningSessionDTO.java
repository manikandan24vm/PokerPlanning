package com.poker.planning.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;
@Schema(description = "Data Transfer Object for Poker Planning Session")
public class PokerPlanningSessionDTO {

    private Long idSession;
    @Schema(description = "Title for the session", example = "Sprint planning-1")
    private String title;
    @JsonIgnore
    private Set<MemberDTO> membersDTO;
    @JsonIgnore
    private Set<UserStoryDTO> userStoriesDTO;

    public PokerPlanningSessionDTO() {
    }

    public PokerPlanningSessionDTO(Long idSession, String title, Set<MemberDTO> membersDTO, Set<UserStoryDTO> userStoriesDTO) {
        this.idSession = idSession;
        this.title = title;
        this.membersDTO = membersDTO;
        this.userStoriesDTO = userStoriesDTO;
    }

    public Long getIdSession() {
        return idSession;
    }

    public void setIdSession(Long idSession) {
        this.idSession = idSession;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<MemberDTO> getMembersDTO() {
        return membersDTO;
    }

    public void setMembersDTO(Set<MemberDTO> membersDTO) {
        this.membersDTO = membersDTO;
    }

    public Set<UserStoryDTO> getUserStoriesDTO() {
        return userStoriesDTO;
    }

    public void setUserStoriesDTO(Set<UserStoryDTO> userStoriesDTO) {
        this.userStoriesDTO = userStoriesDTO;
    }
}
