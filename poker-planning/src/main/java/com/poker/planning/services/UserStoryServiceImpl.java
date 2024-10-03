package com.poker.planning.services;

import com.poker.planning.entities.PokerPlanningSession;
import com.poker.planning.entities.UserStory;
import com.poker.planning.exceptionhandling.UserStoryNotFoundException;
import com.poker.planning.repositories.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserStoryServiceImpl implements UserStoryService{
    @Autowired
    private UserStoryRepository userStoryRepository;
    @Autowired
    private PokerPlanningSessionService pokerPlanningSessionService;
    @Override
    public List<UserStory> getUserStories() {
        return userStoryRepository.findAll();
    }
    @Override
    public UserStory createUserStory(Long sessionId,UserStory userStory) {
        PokerPlanningSession session=pokerPlanningSessionService.getSessionById(sessionId);
        userStory.setSession(session);
        userStory.setStatus(UserStory.Status.PENDING);
        return userStoryRepository.save(userStory);
    }
    @Override
    public UserStory updateUserStory(Long sessionId, Long userStoryId, UserStory userStory) {
        PokerPlanningSession session=pokerPlanningSessionService.getSessionById(sessionId);
        UserStory existingStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new UserStoryNotFoundException("User Story not found"));
        if (!existingStory.getSession().equals(session)) {
            throw new IllegalArgumentException("User Story does not belong to this session");
        }
        existingStory.setDescription(userStory.getDescription());
        existingStory.setStatus(userStory.getStatus());
        return userStoryRepository.save(existingStory);
    }

    @Override
    public void deleteUserStory(Long sessionId, Long userStoryId) {
        PokerPlanningSession session=pokerPlanningSessionService.getSessionById(sessionId);
        UserStory story = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new UserStoryNotFoundException("User Story not found"));
        if (!story.getSession().equals(session)) {
            throw new IllegalArgumentException("User Story does not belong to this session");
        }
        userStoryRepository.deleteUserStoryById(userStoryId);
    }
}
