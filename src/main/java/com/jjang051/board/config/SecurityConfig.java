package com.jjang051.board.config;

import com.jjang051.board.service.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2DetailsService oAuth2DetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web->web.ignoring().requestMatchers(
                "/css/**",
                "/js/**",
                "/images/**",
                "/h2-console/**"
            )
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.authorizeHttpRequests((auth)->auth
                .requestMatchers(
                        "/",
                        "/member/signin",
                        "/member/login"
                )
                .permitAll()
                .anyRequest().authenticated()
        );
        httpSecurity.formLogin((auth)->auth
                .loginPage("/member/login")
                .loginProcessingUrl("/member/login")
                .usernameParameter("userId")          //username
                .passwordParameter("password")        //password
                .defaultSuccessUrl("/board/list",true)
                .permitAll()
        );
        httpSecurity.logout((auth)->auth
                .logoutRequestMatcher(
                        new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
        );
        httpSecurity.oauth2Login((auth)->auth
                .loginPage("/member/login")
                .defaultSuccessUrl("/board/list")
                .userInfoEndpoint(userInfo->
                        userInfo.userService(oAuth2DetailsService)
                )
        );
        httpSecurity.csrf((auth)->auth.disable());
        //필터 설정을 여기다가 하면 된다.
        return httpSecurity.build();
    }
}
