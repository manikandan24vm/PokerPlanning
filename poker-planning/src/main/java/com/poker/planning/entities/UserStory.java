package com.poker.planning.entities;

import jakarta.persistence.*;

import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "user_stories")
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "story_seq", sequenceName = "story_seq", allocationSize = 1)
    @Column(name = "id_user_story",length = 36)
    private Long idUserStory;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Column(name = "vote_count")
    private Long voteCount = 0L;
    @ManyToOne
    @JoinColumn(name = "id_session")

    private PokerPlanningSession session;

    @OneToMany(mappedBy = "userStory", cascade = CascadeType.ALL, orphanRemoval = true)

    private Set<Vote> votes;


        public enum Status {
            PENDING,
            VOTING,
            VOTED
    }

    public UserStory() {
    }

    public UserStory(Long idUserStory, String description, Status status, PokerPlanningSession session, Set<Vote> votes,Long voteCount ) {
        this.idUserStory = idUserStory;
        this.description = description;
        this.status = status;
        this.session = session;
        this.votes = votes;
        this.voteCount=voteCount;

    }
    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    public Long getIdUserStory() {
        return idUserStory;
    }

    public void setIdUserStory(Long idUserStory) {
        this.idUserStory = idUserStory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PokerPlanningSession getSession() {
        return session;
    }

    public void setSession(PokerPlanningSession session) {
        this.session = session;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
}

