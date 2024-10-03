package com.poker.planning.services;

import com.poker.planning.entities.Member;
import com.poker.planning.entities.PokerPlanningSession;
import com.poker.planning.repositories.PokerPlanningSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PokerPlanningSessionServiceImpl implements PokerPlanningSessionService{
    @Autowired
    private PokerPlanningSessionRepository pokerPlanningSessionRepository;

    private static final String SESSION_ID_REGEX = "^[0-9]+$";
    private static final Pattern SESSION_ID_PATTERN = Pattern.compile(SESSION_ID_REGEX);
    @Override
    public List<PokerPlanningSession> getSessions() {
        return pokerPlanningSessionRepository.findAll();
    }

    @Override
    public PokerPlanningSession createSession(PokerPlanningSession session) {
      return pokerPlanningSessionRepository.save(session);
    }

    @Override
    public PokerPlanningSession getSessionById(Long sessionId) {
        return pokerPlanningSessionRepository.findById(sessionId).get();
    }

    @Override
    public void deleteSessionById(Long sessionId) {
        pokerPlanningSessionRepository.deleteById(sessionId);
    }
    @Override
    public boolean isValidId(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        Matcher matcher = SESSION_ID_PATTERN.matcher(id);
        return matcher.matches();
    }

}
