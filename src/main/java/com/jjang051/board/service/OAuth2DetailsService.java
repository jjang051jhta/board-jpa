package com.jjang051.board.service;

import com.jjang051.board.dto.CustomUserDetails;
import com.jjang051.board.social.GoogleUserInfo;
import com.jjang051.board.social.SocialuserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class OAuth2DetailsService extends DefaultOAuth2UserService {

    //  변수들이 들어온다.
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        log.info("구글로그인하면 여기로 들어온다.");

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("userRequest=={}",userRequest);

        Map<String,Object> oath2UserInfo =  (Map)oAuth2User.getAttributes();
        log.info("oAuth2User.getAttributes()=={}",oAuth2User.getAttributes());
        log.info("id==={}",userRequest.getClientRegistration().getRegistrationId());
        String provider = userRequest.getClientRegistration().getRegistrationId();
        //db에 넣어줘야 한다.
        //email, name userName, 임시비밀번호  uuid,
        SocialuserInfo socialuserInfo = null;
        if(provider.equals("google")) {
            socialuserInfo = new GoogleUserInfo(oath2UserInfo);

        }


        return new CustomUserDetails(null,oAuth2User.getAttributes());
    }

}
