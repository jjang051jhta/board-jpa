package com.jjang051.board.entity;

import com.jjang051.board.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String userId;
    private String userName;
    private String email;

    private String password;

    @OneToMany(mappedBy = "writer")
    private List<Board> boards;

    private LocalDateTime regDate;


    @Builder
    public Member(String userId, String userName, String email, String password, List<Board> boards, LocalDateTime regDate) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.boards = boards;
        this.regDate = regDate;
    }

    public static MemberDto fromEntity(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .userId(member.getUserId())
                .userName(member.getUserName())
                .regDate(member.getRegDate())
                .password(member.getPassword())
                .build();
    }
}
