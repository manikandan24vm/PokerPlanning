package com.poker.planning.converters;

import com.poker.planning.dto.PokerPlanningSessionDTO;
import com.poker.planning.dto.UserStoryDTO;
import com.poker.planning.dto.VoteDTO;
import com.poker.planning.dto.api.dto.UserStoryApiDTO;
import com.poker.planning.entities.PokerPlanningSession;
import com.poker.planning.entities.UserStory;
import com.poker.planning.entities.Vote;

import java.util.Set;
import java.util.stream.Collectors;

public class UserStoryConverter {
    public static UserStoryApiDTO toDTO(UserStory userStory) {
        if (userStory == null) {
            return null;
        }
        PokerPlanningSessionDTO sessionDTO = PokerPlanningSessionConverter.toDTO(userStory.getSession());
        UserStoryApiDTO userStoryDTO=new UserStoryApiDTO();
        userStoryDTO.setIdUserStory(userStory.getIdUserStory());
        userStoryDTO.setDescription(userStory.getDescription());
        userStoryDTO.setStatusDTO(UserStoryDTO.StatusDTO.valueOf(userStory.getStatus().name()));
        userStoryDTO.setSessionId(sessionDTO.getIdSession());
        userStoryDTO.setSessionName(sessionDTO.getTitle());
        userStoryDTO.setVoteCount(userStory.getVoteCount());
        return userStoryDTO;
    }

    public static UserStory toEntity(UserStoryDTO userStoryDTO) {
        if (userStoryDTO == null) {
            return null;
        }

//        PokerPlanningSession session = PokerPlanningSessionConverter.toEntity(userStoryDTO.getSessionDTO());
//        Set<Vote> votes = userStoryDTO.getVotesDTO().stream()
//                .map(VotingConverter::toEntity)
//                .collect(Collectors.toSet());
//
//        return new UserStory(
//                userStoryDTO.getIdUserStory(),
//                userStoryDTO.getDescription(),
//                UserStory.Status.valueOf(userStoryDTO.getStatusDTO().name()),
//                session,
//                votes
//        );
        UserStory userStory=new UserStory();
        userStory.setIdUserStory(userStoryDTO.getIdUserStory());
        userStory.setDescription(userStoryDTO.getDescription());
        return userStory;
    }
}


