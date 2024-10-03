package com.poker.planning.services;

import com.poker.planning.entities.Member;
import com.poker.planning.entities.UserStory;
import com.poker.planning.entities.Vote;
import com.poker.planning.exceptionhandling.MemberNotFoundException;
import com.poker.planning.exceptionhandling.UserStoryNotFoundException;
import com.poker.planning.exceptionhandling.VotingNotFoundException;
import com.poker.planning.repositories.MemberRepository;
import com.poker.planning.repositories.UserStoryRepository;
import com.poker.planning.repositories.VotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class VotesServiceImpl implements VotesService {
    @Autowired
    private VotesRepository votesRepository;
    @Autowired
    private UserStoryRepository userStoryRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PokerPlanningSessionService pokerPlanningSessionService;

    @Override
    public UserStory startVoting(Long sessionId, Long userStoryId) {
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new UserStoryNotFoundException("User Story not found"));
        if (!userStory.getSession().getIdSession().equals(sessionId)) {
            throw new IllegalArgumentException("User Story does not belong to this session");
        }
        userStory.setStatus(UserStory.Status.VOTING);
        return userStoryRepository.save(userStory);
    }

    @Override
    @Transactional
    public Vote emitVote(Long sessionId, Long userStoryId, Long memberId, String value) {
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new UserStoryNotFoundException("User Story not found"));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));

        if (userStory.getStatus() != UserStory.Status.VOTING) {
            throw new IllegalStateException("User Story is not in VOTING status");
        }

        Vote existingVote = votesRepository.findByUserStoryAndMember(userStory, member);
        if (existingVote != null) {
            existingVote.setValue(value);
            return votesRepository.save(existingVote);
        } else {
            Vote newVote = new Vote(null, value, member, userStory);
            votesRepository.save(newVote);
            userStory.setVoteCount(userStory.getVoteCount() + 1);
            userStoryRepository.save(userStory);
            return newVote;
        }
    }


    @Override
    @Transactional
    public UserStory stopVoting(Long sessionId, Long userStoryId) {
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new UserStoryNotFoundException("User Story not found"));
        if (!userStory.getSession().getIdSession().equals(sessionId)) {
            throw new IllegalArgumentException("User Story does not belong to this session");
        }
        userStory.setStatus(UserStory.Status.VOTED);
        return userStoryRepository.save(userStory);
    }

    @Override
    public List<Vote> getVoteList(Long sessionId) {
        return votesRepository.findBySessionId(sessionId);
    }

    @Override
    public void deleteVote(Long voteId) {
        votesRepository.deleteVoteById(voteId);
    }

    @Override
    public Vote updateVote(Long voteId, Long userStoryId, Long sessionId, Vote vote) {
        UserStory story = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new UserStoryNotFoundException("User Story not found"));
        Vote existingVote = votesRepository.findById(voteId).orElseThrow(() -> new VotingNotFoundException("Voting not found with ID :" + voteId));
        if (!story.getSession().getIdSession().equals(sessionId)) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        existingVote.setValue(vote.getValue());
        return votesRepository.save(existingVote);
    }
}
