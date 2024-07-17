package com.jjang051.board.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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
    public Board(String title, String content, Member writer, LocalDateTime regDate, Long id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regDate = regDate;
    }


}
