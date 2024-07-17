package com.jjang051.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public Board(String title, String content, Member writer, LocalDateTime regDate) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regDate = regDate;
    }
}
