package com.jjang051.board.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private String content;


    @ManyToOne
    @JoinColumn(name = "writerId",referencedColumnName = "userId")
    private Member writer;

    private LocalDateTime regDate;
}
