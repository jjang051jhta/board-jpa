package com.jjang051.board.controller;

import com.jjang051.board.dto.CustomUserDetails;
import com.jjang051.board.dto.MemberDto;
import com.jjang051.board.entity.Board;
import com.jjang051.board.entity.Comment;
import com.jjang051.board.entity.Member;
import com.jjang051.board.repository.MemberRepository;
import com.jjang051.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signin")
    public String signin() {
        return "member/signin";
    }
    @PostMapping("/signin")
    public String signinProcess(@ModelAttribute MemberDto memberDto) {
        Member savedMember = memberService.siginIn(memberDto);
        MemberDto convertedMemberDto = Member.fromEntity(savedMember);
        log.info("savedMember==={}",convertedMemberDto.toString());
        return "redirect:/member/login";
    }
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/info")
    public String info(Model model,
                       @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        //jjang051
        List<Board> boards =
                memberService.findAllBoards(customUserDetails.getLoggedMember().getUserId());
        List<Comment> comments =
                memberService.findAllComments(customUserDetails.getLoggedMember().getUserId());
        model.addAttribute("infoMember",customUserDetails); //기본 정보들
        model.addAttribute("boards",boards);
        model.addAttribute("comments",comments);

        return "member/info";
    }
}
