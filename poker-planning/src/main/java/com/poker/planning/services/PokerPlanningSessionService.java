package com.poker.planning.services;

import com.poker.planning.entities.Member;
import com.poker.planning.entities.PokerPlanningSession;

import java.util.List;

public interface PokerPlanningSessionService {


    List<PokerPlanningSession> getSessions();
    PokerPlanningSession createSession(PokerPlanningSession session);
    PokerPlanningSession getSessionById(Long sessionId);
    void deleteSessionById(Long sessionId);
    public boolean isValidId(String id);



}
