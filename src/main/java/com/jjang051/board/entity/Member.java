package com.jjang051.board.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String userId;
    private String userName;
    private String email;

    @OneToMany(mappedBy = "writer")
    private List<Board> boards;

    private LocalDateTime regDate;
}
