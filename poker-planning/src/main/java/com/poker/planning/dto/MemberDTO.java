package com.poker.planning.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;
@Schema(description = "Data Transfer Object for Members")
public class MemberDTO {
    private Long idMember;
    @Schema(description = "Name of the member", example = "David")
    private String name;
    @JsonIgnore
    private PokerPlanningSessionDTO sessionDTO;
    @JsonIgnore
    private Set<VoteDTO> votesDTO;

    public MemberDTO() {
    }

    public MemberDTO(Long idMember, String name, PokerPlanningSessionDTO sessionDTO, Set<VoteDTO> votesDTO) {
        this.idMember = idMember;
        this.name = name;
        this.sessionDTO = sessionDTO;
        this.votesDTO = votesDTO;
    }

    public Long getIdMember() {
        return idMember;
    }

    public void setIdMember(Long idMember) {
        this.idMember = idMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
