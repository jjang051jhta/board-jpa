package com.jjang051.board.controller;

import com.jjang051.board.dto.CustomUserDetails;
import com.jjang051.board.entity.Board;
import com.jjang051.board.service.BoardService;
import com.jjang051.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@Slf4j
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

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Map<String,String> deleteAjax(@PathVariable("id") Long id, @RequestParam Long boardId
    ) {
        log.info("id==={}",id);
        //commentService.delete(id);
        //return "redirect:/board/view/"+boardId;  //boardId
        commentService.delete(id);
        Map<String, String> resultMap = new HashMap<>();

        resultMap.put("isDelete","ok");
        return resultMap;
    }
}
