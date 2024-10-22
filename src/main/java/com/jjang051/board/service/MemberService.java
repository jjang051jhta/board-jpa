package com.jjang051.board.service;

import com.jjang051.board.dto.MemberDto;
import com.jjang051.board.entity.Board;
import com.jjang051.board.entity.Comment;
import com.jjang051.board.entity.Member;
import com.jjang051.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member siginIn(MemberDto memberDto) {
//        Member signInMember = Member.builder()
//                                .userId(memberDto.getUserId())
//                                .userName(memberDto.getUserName())
//                                .password(memberDto.getPassword())
//                                .email(memberDto.getEmail())
//                                .regDate(LocalDateTime.now())
//        .build();
        memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
        Member signInMember = MemberDto.toEntity(memberDto);
        //Member에 fromEntity dto
        return memberRepository.save(signInMember);
    }
    private void getMember() {
        memberRepository.findByUserId("jjang051");

    }

    public List<Board> findAllBoards(String userId) {
        return memberRepository.findAllBoards(userId);
    }

    public List<Comment> findAllComments(String userId) {
        return memberRepository.findAllComments(userId);
    }
}
