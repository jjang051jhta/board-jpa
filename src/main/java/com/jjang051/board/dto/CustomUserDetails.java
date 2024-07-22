package com.jjang051.board.dto;

import com.jjang051.board.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
public class CustomUserDetails implements UserDetails, OAuth2User {
    //UserDetails 직접회원가입한 멤버    6개의 기본 정보외 나머지 정보 찾아쓸때
    //소셜로그인 통해서 가입한 유저


    private Member loggedMember;

    private Map<String, Object> attributes;

    public CustomUserDetails(Member loggedMember) {
        this.loggedMember = loggedMember;
    }

    public CustomUserDetails(Member loggedMember, Map<String, Object> attributes) {
        this.loggedMember = loggedMember;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return null;
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return loggedMember.getPassword();
    }

    @Override
    public String getUsername() {
        return loggedMember.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        //소셜 로그인 통해서 들어온 user의 이름
        return (String) attributes.get("name");
    }
}
