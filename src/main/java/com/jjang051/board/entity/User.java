package com.jjang051.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    //@Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String userName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="team_id")
    private Team team;

    public User(String userName, Team team) {
        this.userName = userName;
        this.team = team;
    }

    public User(String userName) {
        this.userName = userName;
    }
}

