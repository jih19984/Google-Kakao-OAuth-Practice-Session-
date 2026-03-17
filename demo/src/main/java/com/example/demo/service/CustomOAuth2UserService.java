package com.example.demo.service;

import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.dto.OAuthUserInfo;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    // 기본 OAuth 사용자 정보 조회를 Spring이 제공하는 구현체에 맡긴다.
    private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // Google/Kakao에서 실제 사용자 정보를 받아온다.
        OAuth2User oauth2User = delegate.loadUser(userRequest);

        // 현재 어떤 provider로 로그인했는지 식별한다. (google, kakao)
        String provider = userRequest.getClientRegistration().getRegistrationId();

        // provider별 응답 구조를 공통 사용자 정보로 변환한다.
        OAuthUserInfo userInfo = extractUserInfo(provider, oauth2User.getAttributes());

        // provider + providerId로 기존 사용자를 찾고,
        // 있으면 이름/사진을 갱신하고 없으면 새로 저장한다.
        User user = userRepository.findByProviderAndProviderId(
                userInfo.getProvider(),
                userInfo.getProviderId()
            )
            .map(existingUser -> {
                existingUser.update(userInfo.getName(), userInfo.getPicture());
                return userRepository.save(existingUser);
            })
            .orElseGet(() -> userRepository.save(
                new User(
                    userInfo.getProvider(),
                    userInfo.getProviderId(),
                    userInfo.getName(),
                    userInfo.getPicture()
                )
            ));

        // 인증 흐름은 계속 Spring Security가 사용하도록 원래 OAuth2User를 반환한다.
        return oauth2User;
    }

    private OAuthUserInfo extractUserInfo(String provider, Map<String, Object> attributes) {
        if ("google".equals(provider)) {
            // Google은 sub, name, picture를 바로 꺼낼 수 있다.
            String providerId = (String) attributes.get("sub");
            String name = (String) attributes.get("name");
            String picture = (String) attributes.get("picture");

            return new OAuthUserInfo(provider, providerId, name, picture);
        }

        if ("kakao".equals(provider)) {
            // Kakao는 id는 최상위에 있고,
            // 이름과 사진은 kakao_account.profile 안에 들어 있다.
            String providerId = String.valueOf(attributes.get("id"));

            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

            String name = (String) profile.get("nickname");
            String picture = (String) profile.get("profile_image_url");

            return new OAuthUserInfo(provider, providerId, name, picture);
        }

        throw new IllegalArgumentException("지원하지 않는 provider입니다: " + provider);
    }
}
