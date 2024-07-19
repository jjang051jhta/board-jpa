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

    @ManyToOne
    @JoinColumn(name = "writerId",referencedColumnName = "userId")
    private Member member;

    @Builder
    public Comment(String content, LocalDateTime regDate, Board board, Member member) {
        this.content = content;
        this.regDate = regDate;
        this.board = board;
        this.member = member;
    }
}
