package com.poker.planning.converters;

import com.poker.planning.dto.MemberDTO;
import com.poker.planning.dto.PokerPlanningSessionDTO;
import com.poker.planning.dto.UserStoryDTO;
import com.poker.planning.entities.Member;
import com.poker.planning.entities.PokerPlanningSession;
import com.poker.planning.entities.UserStory;

import java.util.Set;
import java.util.stream.Collectors;

public class PokerPlanningSessionConverter {
public static PokerPlanningSessionDTO toDTO(PokerPlanningSession session) {
    if (session == null) {
        return null;
    }

//    Set<MemberDTO> membersDTO = session.getMembers().stream()
//            .map(MembersConverter::toDTO)
//            .collect(Collectors.toSet());
//    Set<UserStoryDTO> userStoriesDTO = session.getUserStories().stream()
//            .map(UserStoryConverter::toDTO)
//            .collect(Collectors.toSet());
//
//    return new PokerPlanningSessionDTO(
//            session.getIdSession(),
//            session.getTitle(),
//            membersDTO,
//            userStoriesDTO
//    );
    PokerPlanningSessionDTO planningSessionDTO=new PokerPlanningSessionDTO();
    planningSessionDTO.setIdSession(session.getIdSession());
    planningSessionDTO.setTitle(session.getTitle());
    return planningSessionDTO;
}

    public static PokerPlanningSession toEntity(PokerPlanningSessionDTO sessionDTO) {
        if (sessionDTO == null) {
            return null;
        }

        PokerPlanningSession planningSession=new PokerPlanningSession();
        planningSession.setIdSession(sessionDTO.getIdSession());
        planningSession.setTitle(sessionDTO.getTitle());
        return planningSession;

    }

}
