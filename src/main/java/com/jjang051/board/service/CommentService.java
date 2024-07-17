package com.jjang051.board.service;

import com.jjang051.board.dto.CustomUserDetails;
import com.jjang051.board.entity.Board;
import com.jjang051.board.entity.Comment;
import com.jjang051.board.entity.Member;
import com.jjang051.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    public void insert(String content, Board board,
                       Member member) {
        //content,
        Comment comment =
                Comment.builder()
                                .content(content)
                                .regDate(LocalDateTime.now())
                                .board(board)
                                .member(member)
                        .build();
        commentRepository.save(comment);

    }
}
