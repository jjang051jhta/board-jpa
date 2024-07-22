package com.jjang051.board.social;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class GoogleUserInfo implements SocialuserInfo {

    private final Map<String ,Object> attribute;

    @Override
    public String getEmail() {
        return (String) attribute.get("email");
    }

    @Override
    public String getName() {
        return (String) attribute.get("name");
    }

    @Override
    public String getProvider() {
        return "google";
    }
    @Override
    public String getProviderId() {
        //userId
        return getProvider()+"_"+attribute.get("sub");
        // userId = google_109928619012501307465
        // userName = 장성호
    }
}
