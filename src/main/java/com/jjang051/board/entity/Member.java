package com.jjang051.board.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String userId;
    private String userName;
    private String email;

    private String password;

    @OneToMany(mappedBy = "writer")
    private List<Board> boards;

    private LocalDateTime regDate;

    @Builder
    public Member(String userId, String userName, String email, String password, List<Board> boards, LocalDateTime regDate) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.boards = boards;
        this.regDate = regDate;
    }
}
