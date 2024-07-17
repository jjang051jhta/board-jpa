package com.jjang051.board.controller;

import com.jjang051.board.dto.MemberDto;
import com.jjang051.board.entity.Member;
import com.jjang051.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/signin")
    public String signin() {
        return "member/signin";
    }

    @PostMapping("/signin")
    public String signinProcess(@ModelAttribute MemberDto memberDto) {
        Member signInMember = Member.builder()
                .userId(memberDto.getUserId())
                .userName(memberDto.getUserName())
                .password(memberDto.getPassword())
                .email(memberDto.getEmail())
                .regDate(LocalDateTime.now())
                .build();
        memberRepository.save(signInMember);
        return "redirect:/member/login";
    }
}
