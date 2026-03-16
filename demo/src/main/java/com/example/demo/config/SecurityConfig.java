package com.example.demo.config;

import org.springframework.context.annotation.Bean; 

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// Bean: 메서드가 반환하는 객체를 스프링이 관리하는 객체 빈으로 설정
// Configuration: 이 클래스가 설정 파일 역할을 하는 클래스
// Customizer: 기본 설정을 간단히 적용할 때 쓰는 도우미
// HttpSecurity: 스프링 시큐리티에서 HTTP 요청에 대한 보안 규칙을 설정하는 객체
// SecurityFilterChain: 스프링 시큐리티가 어떤 필터들을 어떤 규칙으로 동작시킬지 정리한 보안 체인 (필터들의 묶음과 규칙)
// CSRF: 사용자의 브라우저를 속여서 몰래 대리 서명하게 만드는 것.

// HttpSecurity http: 메서드가 받아오는 보안 설정 객체
// throws Exception: 설정 중 예외가 날 수 있음
// requestMatchers: 어떤 URL 요청에 이 규칙을 적용할지 지정

public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        // csrf 잠시 끄기
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // /, /login, /error는 로그인 없이 접근 가능
                // **는 와일드카드 패턴, 이 뒤에 뭐가 오든 다 포함한다.
                .requestMatchers("/", "/login", "/error").permitAll()
                .requestMatchers("/oauth2/authorization/**").permitAll()
                // 이외의 요청은 인증된 사용자만 접근 가능
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .oauth2Login(oauth2 -> oauth2
                .defaultSuccessUrl("http://localhost:5173", true)
            )                      
            .logout(logout -> logout
                  // 로그아웃이 성공하면 경로를 이동시키겠다.
                .logoutSuccessUrl("/")
            );

            // http에 적어놓은 보안 설정들을 실제 SecurityFilterChain 객체로 만들어서 반환.
            // 이후 SpringSecurity가 자동으로 사용
            return http.build();
    }
}
