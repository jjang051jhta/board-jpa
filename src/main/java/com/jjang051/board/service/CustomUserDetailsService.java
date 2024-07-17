package com.jjang051.board.service;

import com.jjang051.board.dto.CustomUserDetails;
import com.jjang051.board.entity.Member;
import com.jjang051.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByUserId(userId);
        log.info(optionalMember.get().toString());
        if(optionalMember.isPresent()) {
            return new CustomUserDetails(optionalMember.get());  //Member
        }
        throw new UsernameNotFoundException("아이디 패스워드 확인해주세요.");
        //return null;
    }
}
