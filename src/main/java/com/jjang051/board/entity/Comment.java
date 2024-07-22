package com.jjang051.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String content;
    private LocalDateTime regDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writerId",referencedColumnName = "userId")
    private Member writer;

    @Builder
    public Comment(String content, LocalDateTime regDate, Board board, Member writer) {
        this.content = content;
        this.regDate = regDate;
        this.board = board;
        this.writer = writer;
    }
}
