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
    @GeneratedValue
    //@Column(name = "team_id")
    private Long id;
    private String name;
    private String userName;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;
}

