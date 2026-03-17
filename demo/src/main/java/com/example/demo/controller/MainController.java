package com.example.demo.controller;

// RestController는 일반 Controller와 달리 JSON 형식으로 데이터를 내보냄.
// 컨트롤러는 브라우저에서 들어오는 신호를 가장 먼저 맞이한다.
// AuthenticationPrincipal 현재 로그인한 사용자의 객체를 주입받는 표시
// OAuth2User OAuth2 로그인으로 인증된 사용자 정보를 담고 있는 객체 타입
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;


@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class MainController {

    @GetMapping("/api/hello")
    public String hello() {
        return "hello from spring";
    }
    
    @GetMapping("/api/user")
    // 이 메서드는 Map<String, Object>를 반환한다.
    // 메서드 이름은 user
    // @AuthenticationPrincipal OAuth2User oauth2User
    // 현재 로그인한 OAuth 정보를 oauth2User로 받는다.
    // Map<String, Object>는 키와 값 형태로 데이터를 담는 자바 자료형
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User oauth2User) {
        if (oauth2User == null) {
            return Map.of("authenticated", false);
        }

        Map<String, Object> attributes = oauth2User.getAttributes();

        String name = null;
        String picture = null;

        if (attributes.get("kakao_account") != null) {
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

            name = (String) profile.get("nickname");
            picture = (String) profile.get("profile_image_url");
        } else {
            name = (String) attributes.get("name");
            picture = (String) attributes.get("picture");
        }
        // oauth2User.getAttribute()는 OAuth 로그인한 사용자의 정보를 꺼내오는 메서드.
        return Map.of(
    "authenticated", true,
    "name", name,
    "picture", picture
);
    }                               
}
 