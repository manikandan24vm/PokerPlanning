package com.poker.planning.repositories;

import com.poker.planning.entities.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserStoryRepository extends JpaRepository<UserStory,Long> {
    @Modifying
    @Transactional
    @Query("DELETE from UserStory where idUserStory = :userStoryId")
    void deleteUserStoryById(@Param("userStoryId") Long userStoryId);
}
