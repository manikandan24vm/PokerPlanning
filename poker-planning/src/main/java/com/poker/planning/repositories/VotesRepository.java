package com.poker.planning.repositories;

import com.poker.planning.entities.Member;
import com.poker.planning.entities.UserStory;
import com.poker.planning.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface VotesRepository extends JpaRepository<Vote,Long> {
    @Query("SELECT v FROM Vote v WHERE v.userStory.session.idSession = :sessionId")
    List<Vote> findBySessionId(@Param("sessionId") Long sessionId);
    @Modifying
    @Transactional
    @Query("DELETE from Vote where idVote =:voteId")
    void deleteVoteById(@Param("voteId") Long voteId);
    @Query("SELECT v FROM Vote v WHERE v.userStory = :userStory AND v.member = :member")
    Vote findByUserStoryAndMember(UserStory userStory, Member member);


    @Query("SELECT v.value AS value, COUNT(v) AS count FROM Vote v WHERE v.userStory = :userStory GROUP BY v.value")
    Map<String, Long> calculateVoteSummary(@Param("userStory") UserStory userStory);
}
