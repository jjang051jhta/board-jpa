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
        //dto로 바꿔서 나가야함... entity를 직접 내보내는건 좋지 않다.  dto
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
    //@ResponseBody
    public String delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        //delete 를 하면 된다.
        log.info("id==={}",id);
        //return id+"==="+id;
        return "redirect:/board/list";
    }

    @GetMapping("/deleteid/{id}")
    //@ResponseBody
    public String deleteId(@PathVariable("id") Long id) {
        boardService.deleteId(id);
        //delete 를 하면 된다.
        log.info("id==={}",id);
        //return id+"==="+id;
        return "redirect:/board/list";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long id,Model model) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board",board);
        return "board/modify";
    }

    @PostMapping("/modify/{id}")
    public String modifyProcess(@PathVariable("id") Long id,
                                @ModelAttribute BoardDto boardDto,
                                @AuthenticationPrincipal
                                    CustomUserDetails customUserDetails
                                ) {
        //같으면 업데이트를 한다.
        //같은 객체를 내용만 바꿔서 save 하면   dirty checking
        log.info("===={}",boardDto.getId());
        boardDto.setRegDate(LocalDateTime.now());
        boardDto.setWriter(customUserDetails.getLoggedMember());
        boardService.modify(boardDto,id);
        return "redirect:/board/modify/"+id;
    }
}
