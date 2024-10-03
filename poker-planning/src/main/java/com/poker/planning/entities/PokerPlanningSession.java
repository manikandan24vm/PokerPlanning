package com.poker.planning.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@Table(name = "poker_planning_sessions")
public class PokerPlanningSession {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "session_seq", sequenceName = "session_seq", allocationSize = 1)
    @Column(name = "id_session",length = 36)
    private Long idSession;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)

    private Set<Member> members;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)

    private Set<UserStory> userStories;

    public PokerPlanningSession() {
    }

    public PokerPlanningSession(Long idSession, String title, Set<Member> members, Set<UserStory> userStories) {
        this.idSession = idSession;
        this.title = title;
        this.members = members;
        this.userStories = userStories;
    }

    public Long getIdSession() {
        return idSession;
    }

    public void setIdSession(Long idSession) {
        this.idSession = idSession;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public Set<UserStory> getUserStories() {
        return userStories;
    }

    public void setUserStories(Set<UserStory> userStories) {
        this.userStories = userStories;
    }
}

