package com.jjang051.board.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private String content;


    @ManyToOne
    @JoinColumn(name = "writerId",referencedColumnName = "userId")
    private Member writer;

    // comments 가 없으면 상관이 없는데 참조를 하고 있으면 지워지지 않는다.
    @OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE)
    private List<Comment> comments;


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
