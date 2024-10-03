package com.poker.planning.dto.api.dto;

public class VoteApiDTO {
    private Long idVote;
    private String value;
    private Long sessionId;
    private Long memberId;
    private String memberName;
    private Long storyId;
    private String storyDescription;

    public VoteApiDTO(Long id, String value, Long sessionId, Long memberId, String memberName, Long storyId, String storyName) {
        this.idVote = id;
        this.value = value;
        this.sessionId = sessionId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.storyId = storyId;
        this.storyDescription = storyName;
    }

    public VoteApiDTO() {
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

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Long getStoryId() {
        return storyId;
    }

    public void setStoryId(Long storyId) {
        this.storyId = storyId;
    }

    public String getStoryDescription() {
        return storyDescription;
    }

    public void setStoryDescription(String storyDescription) {
        this.storyDescription = storyDescription;
    }
}
