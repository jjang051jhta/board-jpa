package com.jjang051.board.social;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class GithubUserInfo implements SocialuserInfo {

    private final Map<String ,Object> attribute;  // 맵

    @Override
    public String getEmail() {
        return "이메일 입력해주세요";
    }

    @Override
    public String getName() {
        return (String) attribute.get("login");
    }

    @Override
    public String getProvider() {
        return "github";
    }
    @Override
    public String getProviderId() {
        //userId
        return getProvider()+"_"+attribute.get("id");
        // userId = google_109928619012501307465
        // userName = 장성호
    }
}
