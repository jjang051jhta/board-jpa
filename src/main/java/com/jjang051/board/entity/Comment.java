package com.jjang051.board.entity;

import jakarta.persistence.*;

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

}
