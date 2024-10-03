package com.poker.planning.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;
@Schema(description = "Data Transfer Object for User Story")
public class UserStoryDTO {

    private Long idUserStory;
    @Schema(description = "Description for the user story", example = "user story-1")
    private String description;
    @JsonIgnore
    private PokerPlanningSessionDTO sessionDTO;
    @JsonIgnore
    private Set<VoteDTO> votesDTO;
    @Schema(description = "Status of the user story", example = "PENDING")
    @JsonIgnore
    private StatusDTO statusDTO;


    public UserStoryDTO() {
    }

    public UserStoryDTO(Long idUserStory, String description, PokerPlanningSessionDTO sessionDTO, Set<VoteDTO> votesDTO, StatusDTO statusDTO) {
        this.idUserStory = idUserStory;
        this.description = description;
        this.sessionDTO = sessionDTO;
        this.votesDTO = votesDTO;
        this.statusDTO=statusDTO;
    }

    public enum StatusDTO {
        PENDING,
        VOTING,
        VOTED

    }

    public StatusDTO getStatusDTO() {
        return statusDTO;
    }

    public void setStatusDTO(StatusDTO statusDTO) {
        this.statusDTO = statusDTO;
    }

    public Long getIdUserStory() {
        return idUserStory;
    }

    public void setIdUserStory(Long idUserStory) {
        this.idUserStory = idUserStory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PokerPlanningSessionDTO getSessionDTO() {
        return sessionDTO;
    }

    public void setSessionDTO(PokerPlanningSessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
    }

    public Set<VoteDTO> getVotesDTO() {
        return votesDTO;
    }

    public void setVotesDTO(Set<VoteDTO> votesDTO) {
        this.votesDTO = votesDTO;
    }
}
