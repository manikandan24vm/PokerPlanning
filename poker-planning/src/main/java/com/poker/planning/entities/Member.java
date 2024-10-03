package com.poker.planning.entities;

import jakarta.persistence.*;


import java.util.Set;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "member_seq", sequenceName = "member_seq", allocationSize = 1)
    @Column(name = "id_member",length = 36)
    private Long idMember;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_session")

    private PokerPlanningSession session;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)

    private Set<Vote> votes;

    public Member() {
    }

    public Member(Long idMember, String name, PokerPlanningSession session, Set<Vote> votes) {
        this.idMember = idMember;
        this.name = name;
        this.session = session;
        this.votes = votes;
    }

    public Long getIdMember() {
        return idMember;
    }

    public void setIdMember(Long idMember) {
        this.idMember = idMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
