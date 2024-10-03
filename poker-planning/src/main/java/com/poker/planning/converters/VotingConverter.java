package com.poker.planning.converters;

import com.poker.planning.dto.MemberDTO;
import com.poker.planning.dto.VoteDTO;
import com.poker.planning.dto.api.dto.UserStoryApiDTO;
import com.poker.planning.dto.api.dto.VoteApiDTO;
import com.poker.planning.entities.Vote;

public class VotingConverter {
    public static VoteApiDTO toDTO(Vote vote) {
        if (vote == null) {
            return null;
        }

        MemberDTO memberDTO = MembersConverter.toDTO(vote.getMember());
        UserStoryApiDTO userStoryDTO = UserStoryConverter.toDTO(vote.getUserStory());
        VoteApiDTO voteDTO=new VoteApiDTO();
        voteDTO.setIdVote(vote.getIdVote());
        voteDTO.setValue(vote.getValue());
        voteDTO.setSessionId(userStoryDTO.getSessionId());
        voteDTO.setMemberId(memberDTO.getIdMember());
        voteDTO.setMemberName(memberDTO.getName());
        voteDTO.setStoryId(userStoryDTO.getIdUserStory());
        voteDTO.setStoryDescription(userStoryDTO.getDescription());
        return voteDTO;
    }

    public static Vote toEntity(VoteDTO voteDTO) {
        if (voteDTO == null) {
            return null;
        }
//
//        Member member = MembersConverter.toEntity(voteDTO.getMemberDTO());
//        UserStory userStory = UserStoryConverter.toEntity(voteDTO.getUserStoryDTO());
//
//        return new Vote(
//                voteDTO.getIdVote(),
//                voteDTO.getValue(),
//                member,
//                userStory
//        );
        Vote vote=new Vote();
        vote.setIdVote(voteDTO.getIdVote());
        vote.setValue(voteDTO.getValue());
        return vote;
    }
}


