package com.poker.planning.services;

import com.poker.planning.entities.Member;

import java.util.List;

public interface MemberService {
    Member joinSession(Long sessionId, Member member);
    List<Member> getMembersInSession(Long sessionId);
    void logoutMember(Long sessionId, Long memberId);

    Member updateMember(Long sessionId,Long memberId, Member member);
}
