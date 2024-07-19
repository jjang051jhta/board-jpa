package com.jjang051.board.controller;

import com.jjang051.board.dto.CustomUserDetails;
import com.jjang051.board.entity.Board;
import com.jjang051.board.service.BoardService;
import com.jjang051.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final BoardService boardService;
    @PostMapping("/insert/{id}")
    public String insert(@PathVariable("id") Long id,
                         @RequestParam String content,
                         @AuthenticationPrincipal CustomUserDetails customUserDetails
                         ) {
        Board board = boardService.getBoard(id);
        commentService.insert(content,board,customUserDetails.getLoggedMember());
        return "redirect:/board/view/"+id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,@RequestParam Long boardId
    ) {
        commentService.delete(id);
        return "redirect:/board/view/"+boardId;  //boardId
    }


}
