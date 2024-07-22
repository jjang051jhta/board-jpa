package com.jjang051.board.social;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class KakaoUserInfo implements SocialuserInfo {

    private final Map<String,Object> attributes;
    @Override
    public String getEmail() {
        //비즈앱으로 전환하면 들어온다.
        return "이메일 등록해주세요.";
    }

    @Override
    public String getName() {
        Map<String,Object> properties = (Map)attributes.get("properties");
        return (String)properties.get("nickname");
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return getProvider()+"_"+attributes.get("id");
    }
}
