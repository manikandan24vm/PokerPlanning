package com.poker.planning.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object for Votes")
public class VoteDTO {

    private Long idVote;
    @Schema(description = "Value", example = "3")
    private String value;
    @JsonIgnore
    private MemberDTO memberDTO;
    @JsonIgnore
    private UserStoryDTO userStoryDTO;

    public VoteDTO() {
    }

    public VoteDTO(Long idVote, String value, MemberDTO memberDTO, UserStoryDTO userStoryDTO) {
        this.idVote = idVote;
        this.value = value;
        this.memberDTO = memberDTO;
        this.userStoryDTO = userStoryDTO;
    }

    public Long getIdVote() {
        return idVote;
    }

    public void setIdVote(Long idVote) {
        this.idVote = idVote;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MemberDTO getMemberDTO() {
        return memberDTO;
    }

    public void setMemberDTO(MemberDTO memberDTO) {
        this.memberDTO = memberDTO;
    }

    public UserStoryDTO getUserStoryDTO() {
        return userStoryDTO;
    }

    public void setUserStoryDTO(UserStoryDTO userStoryDTO) {
        this.userStoryDTO = userStoryDTO;
    }
}
