package com.example.demo.config;

import java.util.List;

import org.springframework.context.annotation.Bean; 

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import com.example.demo.service.CustomOAuth2UserService;

// 아래는 CORS 설정을 자바 코드로 만들기 위한 클래스들
// CorsConfiguration, CORS 규칙 내용 자체를 담는 객체
// CorsConfigurationSource, CORS 설정을 어디서 가져올지 알려주는 인터페이스(CORS 설정 제공자)
// UrlBasedCorsConfigurationSource, URL 경로별로 CORS 설정을 연결해주는 구현체
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// Bean: 메서드가 반환하는 객체를 스프링이 관리하는 객체 빈으로 등록
// Configuration: 이 클래스가 설정 파일 역할을 하는 클래스
// Customizer: 기본 설정을 간단히 적용할 때 쓰는 도우미
// HttpSecurity: 스프링 시큐리티에서 HTTP 요청에 대한 보안 규칙을 설정하는 객체
// SecurityFilterChain: 스프링 시큐리티가 어떤 필터들을 어떤 규칙으로 동작시킬지 정리한 보안 체인 (필터들의 묶음과 규칙)
// CSRF: 사용자의 브라우저를 속여서 몰래 대리로 실행하게 만드는 것

// HttpSecurity http: 메서드가 받아오는 보안 설정 객체
// throws Exception: 설정 중 예외가 날수도 있음
// requestMatchers: 어떤 URL 요청에 규칙을 적용할지 지정

// List.of(...)는 리스트를 간단하게 바로 만들어주는 정적 메서드

// 정적 팩토리 메서드는
// new로 생성하는 것을 권장하지 않는게 보통.
// 정적 팩토리 메서드의 장점은 사용하는 쪽이 내부로직을 감춰져 있어, 외부는 결과를 얻을 수 있다.

@Configuration
public class SecurityConfig {

    // CustomOAuth2UserService를 SecurityConfig에 넣어주는 부분
    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())
        // csrf 임시 끄기
            .csrf(csrf -> csrf.disable())
            // h2 콘솔 iframe 허용
            .headers(headers -> headers
                .frameOptions(frame -> frame.disable())
            )
            .authorizeHttpRequests(auth -> auth
                // /, /login, /error는 로그인 없이 접근 가능
                // **는 와일드카드 패턴, 이 뒤에 뭐가 오든 다 포함한다.
                .requestMatchers("/", "/login", "/error").permitAll()
                .requestMatchers("/oauth2/authorization/**").permitAll()
                .requestMatchers("/api/hello", "/api/user").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                // 이외의 요청은 인증된 사용자만 접근 가능
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfo -> userInfo
                    // DB 저장 로직이 실제 실행되게 만듦
                    .userService(customOAuth2UserService)
                )
                .defaultSuccessUrl("http://localhost:5173", true)
            )                      
            .logout(logout -> logout
                  // 로그아웃후에 성공하면 경로를 이동시켜준다.
                .logoutSuccessUrl("http://localhost:5173")
            );

            // http에 적어놓은 보안 설정들을 실제 SecurityFilterChain 객체로 만들어서 반환.
            // 이후 SpringSecurity가 자동으로 사용
            return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // List.of에서 .of는 리스트를 간단하게 바로 만들어주는 정적 메서드
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // 브라우저는 다른 출처(5173 -> 8080)로 요청할 때
        // 헤더까지 검사합니다.
        // 허용 안된 헤더가 있으면 CORS 에러가 발생할수도 있습니다.
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        // 방금 만든 CORS 규칙을 어떤 URL들에 적용할지 등록하는 부분
        // /** 모든 경로에 대해서 CORS 규칙을 쓰겠다. */
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        // spring에게 이 소스를 넘겨준다
        return source;
    }
}
