package com.poker.planning.services;

import com.poker.planning.entities.UserStory;

import java.util.List;

public interface UserStoryService {

    List<UserStory> getUserStories();

    UserStory createUserStory(Long sessionId,UserStory userStory);

    UserStory updateUserStory(Long sessionId, Long userStoryId, UserStory userStory);

    void deleteUserStory(Long sessionId, Long userStoryId);
}
