package com.poker.planning.services;

import com.poker.planning.entities.UserStory;
import com.poker.planning.entities.Vote;

import java.util.List;

public interface VotesService {
    UserStory startVoting(Long sessionId,Long userStoryId);
    Vote emitVote(Long sessionId,Long userStoryId, Long memberId, String value);

    UserStory stopVoting(Long sessionId,Long userStoryId);

    List<Vote> getVoteList(Long sessionId);

    void deleteVote(Long voteId);

    Vote updateVote(Long voteId, Long userStoryId,Long sessionId, Vote vote);
}
