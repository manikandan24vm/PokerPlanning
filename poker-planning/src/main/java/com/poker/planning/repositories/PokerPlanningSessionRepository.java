package com.poker.planning.repositories;

import com.poker.planning.entities.PokerPlanningSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokerPlanningSessionRepository extends JpaRepository<PokerPlanningSession,Long> {
}
