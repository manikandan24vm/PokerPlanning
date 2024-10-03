package com.poker.planning.repositories;

import com.poker.planning.entities.Member;
import com.poker.planning.entities.PokerPlanningSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("SELECT m FROM Member m WHERE m.session = :session")
    List<Member> findBySession(@Param("session") PokerPlanningSession session);
    @Modifying
    @Transactional
    @Query("DELETE from Member where idMember = :memberId")
    void deleteMemberById(@Param("memberId") Long memberId);
}
