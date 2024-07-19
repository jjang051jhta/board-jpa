package com.jjang051.board.controller;

import com.jjang051.board.dto.BoardDto;
import com.jjang051.board.dto.CustomUserDetails;
import com.jjang051.board.entity.Board;
import com.jjang051.board.repository.BoardRepository;
import com.jjang051.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/write")
    public String insert() {
        return "board/write";
    }


    @PostMapping("/write")
    public String insert(@ModelAttribute BoardDto boardDto,
                         @AuthenticationPrincipal CustomUserDetails customUserDetails)    {
        boardDto.setRegDate(LocalDateTime.now());
        boardDto.setWriter(customUserDetails.getLoggedMember());
        boardService.insertBoard(boardDto);
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boardList = boardService.getBoardList();
        model.addAttribute("boardList",boardList);
        //service 파트로 옮기기
        // board를 dto변경
        // 화면 출력
        return "board/list";
    }

    @GetMapping("/view/{id}")
    public String list(Model model, @PathVariable("id") Long id) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board",board);
        //view에다가 렌더링 하시고 답글 넣기 해보기
        return "board/view";
    }


    @GetMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        //delete 를 하면 된다.
        log.info("id==={}",id);
        return id+"==="+id;
    }
}
