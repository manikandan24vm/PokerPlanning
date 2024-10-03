package com.poker.planning.services;

import com.poker.planning.entities.Member;
import com.poker.planning.entities.PokerPlanningSession;
import com.poker.planning.entities.UserStory;
import com.poker.planning.exceptionhandling.MemberNotFoundException;
import com.poker.planning.exceptionhandling.UserStoryNotFoundException;
import com.poker.planning.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PokerPlanningSessionService pokerPlanningSessionService;
    @Override
    public Member joinSession(Long sessionId, Member member) {
        PokerPlanningSession session=pokerPlanningSessionService.getSessionById(sessionId);
        member.setSession(session);
        return memberRepository.save(member);
    }

    @Override
    public List<Member> getMembersInSession(Long sessionId) {
        PokerPlanningSession session=pokerPlanningSessionService.getSessionById(sessionId);
        return memberRepository.findBySession(session);
    }

    @Override
    public void logoutMember(Long sessionId, Long memberId) {
        PokerPlanningSession session=pokerPlanningSessionService.getSessionById(sessionId);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));
        if (!member.getSession().equals(session)) {
            throw new IllegalArgumentException("Member does not belong to this session");
        }
            memberRepository.deleteMemberById(memberId);
    }

    @Override
    public Member updateMember(Long sessionId,Long memberId, Member member) {
        PokerPlanningSession session=pokerPlanningSessionService.getSessionById(sessionId);
        Member existingMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new UserStoryNotFoundException("member not found"));
        if (!existingMember.getSession().equals(session)) {
            throw new IllegalArgumentException("member does not belong to this session");
        }
        existingMember.setName(member.getName());
        return memberRepository.save(existingMember);
    }
}
