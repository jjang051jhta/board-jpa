package com.jjang051.board.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String content;
    private LocalDateTime regDate;

    @ManyToOne
    private Board board;

    @ManyToOne
    private Member member;

    @Builder
    public Comment(String content, LocalDateTime regDate, Board board, Member member) {
        this.content = content;
        this.regDate = regDate;
        this.board = board;
        this.member = member;
    }
}
