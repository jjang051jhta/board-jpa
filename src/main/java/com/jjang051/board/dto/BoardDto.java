package com.jjang051.board.dto;

import com.jjang051.board.entity.Board;
import com.jjang051.board.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private Member writer;
    private LocalDateTime regDate;

    @Builder
    public BoardDto(Long id, String title, String content, Member writer, LocalDateTime regDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regDate = regDate;
    }

    public static Board toEntity(BoardDto boardDto) {
        return Board.builder()
                .id(boardDto.getId())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter())
                .regDate(boardDto.getRegDate())
                .build();
    }
}
