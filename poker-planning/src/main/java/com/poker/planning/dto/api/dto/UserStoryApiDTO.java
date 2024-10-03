package com.poker.planning.dto.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poker.planning.dto.PokerPlanningSessionDTO;
import com.poker.planning.dto.UserStoryDTO;
import com.poker.planning.dto.VoteDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import java.util.Set;

public class UserStoryApiDTO {
    private Long idUserStory;

    private String description;
    @JsonIgnore
    private PokerPlanningSessionDTO sessionDTO;
    @JsonIgnore
    private Set<VoteDTO> votesDTO;

    private UserStoryDTO.StatusDTO statusDTO;

    private Long sessionId;

    private String sessionName;

    private Long voteCount;
    public enum StatusDTO {
        PENDING,
        VOTING,
        VOTE

    }

    public UserStoryApiDTO() {
    }

    public UserStoryApiDTO(Long idUserStory, String description, PokerPlanningSessionDTO sessionDTO, Set<VoteDTO> votesDTO, UserStoryDTO.StatusDTO statusDTO, Long sessionId, String sessionName,Long voteCount) {
        this.idUserStory = idUserStory;
        this.description = description;
        this.sessionDTO = sessionDTO;
        this.votesDTO = votesDTO;
        this.statusDTO = statusDTO;
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.voteCount=voteCount;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
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

    public UserStoryDTO.StatusDTO getStatusDTO() {
        return statusDTO;
    }

    public void setStatusDTO(UserStoryDTO.StatusDTO statusDTO) {
        this.statusDTO = statusDTO;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }
}
