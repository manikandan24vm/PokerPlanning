package com.poker.planning.converters;

import com.poker.planning.dto.MemberDTO;
import com.poker.planning.dto.PokerPlanningSessionDTO;
import com.poker.planning.dto.VoteDTO;
import com.poker.planning.entities.Member;
import com.poker.planning.entities.PokerPlanningSession;
import com.poker.planning.entities.Vote;

import java.util.Set;
import java.util.stream.Collectors;

public class MembersConverter {
        public static MemberDTO toDTO(Member member) {
            if (member == null) {
                return null;
            }

            MemberDTO memberDTO=new MemberDTO();
            memberDTO.setIdMember(member.getIdMember());
            memberDTO.setName(member.getName());
            return memberDTO;
        }

        public static Member toEntity(MemberDTO memberDTO) {
            if (memberDTO == null) {
                return null;
            }

//
//            PokerPlanningSession session = PokerPlanningSessionConverter.toEntity(memberDTO.getSessionDTO());
//            Set<Vote> votes = memberDTO.getVotesDTO().stream()
//                    .map(VotingConverter::toEntity)
//                    .collect(Collectors.toSet());
//
//            return new Member(
//                    memberDTO.getIdMember(),
//                    memberDTO.getName(),
//                    session,
//                    votes
//            );

            Member member=new Member();
            member.setIdMember(memberDTO.getIdMember());
            member.setName(memberDTO.getName());
            return member;
        }


}
