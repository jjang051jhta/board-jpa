package com.jjang051.board.controller;

import com.jjang051.board.dto.CustomUserDetails;
import com.jjang051.board.entity.Board;
import com.jjang051.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardRepository boardRepository;
    @GetMapping("/write")
    public String insert() {
        return "board/write";
    }


    @PostMapping("/write")
    public String insert(@RequestParam String title, @RequestParam String content,
                         @AuthenticationPrincipal CustomUserDetails customUserDetails)    {
        Board saveBoard = Board.builder()
                .title(title)
                .content(content)
                .writer(customUserDetails.getLoggedMember())
                .build();
        boardRepository.save(saveBoard);
        return "redirect:/board/write";
    }



}
