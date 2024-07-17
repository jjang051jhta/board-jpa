package com.jjang051.board.dto;

import com.jjang051.board.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MemberDto {
    private Long id;
    private String userId;
    private String userName;
    private String password;
    private String email;
    private LocalDateTime regDate;

    @Builder
    public MemberDto(Long id, String userId, String userName, String password, String email, LocalDateTime regDate) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.regDate = regDate;
    }

    public static Member toEntity(MemberDto memberDto) {
        return Member.builder()
                .userId(memberDto.getUserId())
                .userName(memberDto.getUserName())
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .regDate(LocalDateTime.now())
                .build();
    }
}
