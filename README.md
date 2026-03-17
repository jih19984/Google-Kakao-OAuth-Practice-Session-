# OAuth Practice Checklist

Google, Kakao 로그인을 Spring Boot + React 구조에서 직접 연습하기 위한 체크리스트입니다.

## 현재 구조

- `demo`: Spring Boot 백엔드
- `frontend`: React 프론트엔드

## 1. 기본 환경 확인

- [x] Spring Boot 프로젝트 생성
- [x] React 프로젝트 생성
- [x] Spring Boot 앱 정상 실행 확인
- [x] React 앱 정상 실행 확인
- [x] `http://localhost:8080` 과 `http://localhost:5173` 역할 구분 이해

## 2. 백엔드 기초 다지기

- [x] `MainController` 패키지 경로와 `package` 선언 일치시키기
- [x] 테스트용 API 만들기
- [x] 문자열 응답과 JSON 응답 차이 이해하기
- [x] Postman 또는 브라우저로 API 응답 확인하기
- [x] React에서 호출할 API 경로를 `/api/...` 형태로 정리하기

## 3. 프론트엔드 기초 다지기

- [x] `App.jsx`에서 `useState`, `useEffect`, `fetch` 흐름 이해
- [x] Spring API를 호출해서 화면에 출력하기
- [x] `loading`, `success`, `error` 상태를 나눠서 처리하기
- [x] 로그인 버튼 UI 만들기
- [x] 사용자 정보가 들어올 영역 설계하기

## 4. Spring Security 기본 이해

- [x] Spring Security가 요청을 필터에서 가로챈다는 흐름 이해
- [x] 세션과 `SecurityContext` 개념 이해
- [x] `AuthenticationManager` 역할 이해
- [x] 기본 로그인 페이지가 왜 뜨는지 정리하기
- [x] 인증과 인가 차이 설명할 수 있게 정리하기
- [x] 세션 기반 로그인 흐름을 말로 설명해보기

## 5. Spring Security 설정 준비

- [x] `spring-boot-starter-security` 의존성 확인
- [x] `spring-boot-starter-oauth2-client` 의존성 확인
- [x] `SecurityConfig` 클래스 만들기
- [x] 정적 리소스와 공개 API 허용 범위 정리하기
- [x] 로그인 성공 후 이동 경로 설계하기
- [x] 로그아웃 후 이동 경로 설계하기

## 6. Google 로그인 구현

- [x] Google Cloud Console에서 OAuth 앱 등록
- [x] 승인된 리디렉션 URI 설정
- [x] 클라이언트 ID, 클라이언트 시크릿 발급
- [x] `application.properties`에 Google 설정 추가
- [x] Spring Security에서 Google 로그인 연결 확인
- [x] 로그인 성공 시 사용자 정보가 들어오는지 확인
- [x] 사용자 이름, 이메일, 프로필 이미지 중 어떤 값을 쓸지 정리하기

## 7. Kakao 로그인 구현

- [x] Kakao Developers에서 애플리케이션 등록
- [x] 플랫폼, Redirect URI 설정
- [x] REST API 키와 시크릿 정보 확인
- [x] Spring Security에 Kakao provider/client 설정 추가
- [x] Kakao 사용자 정보 응답 구조 확인
- [x] Google과 Kakao의 사용자 정보 구조 차이 정리
- [x] Kakao 로그인 성공 여부 확인

## 8. OAuth 사용자 정보 처리

- [x] OAuth 로그인 성공 시 사용자 정보를 어디까지 저장할지 결정
- [x] 세션에 저장할 정보와 DB에 저장할 정보 구분하기
- [x] `OAuth2UserService` 또는 커스텀 사용자 처리 방식 학습
- [x] provider 별로 다른 응답 형식을 하나의 사용자 객체로 매핑하기
- [x] 처음 로그인한 사용자를 가입 처리할지 정책 정하기
- [x] 기존 사용자인지 판별하는 기준 정하기

## 9. React 로그인 화면 연결

- [x] Google 로그인 버튼 만들기
- [x] Kakao 로그인 버튼 만들기
- [x] 버튼 클릭 시 백엔드 OAuth 진입 URL로 이동시키기
- [x] 로그인 성공 후 React 화면으로 돌아오는 흐름 확인
- [x] 로그인된 사용자 정보 표시하기
- [x] 로그아웃 버튼 만들기
- [x] 로그인 전/후 화면 분기 처리하기

## 10. 백엔드-프론트 연동 안정화

- [x] CORS 설정 이해하고 필요한 범위만 허용하기
- [x] 세션/쿠키가 브라우저에서 어떻게 전달되는지 확인
- [x] React에서 인증된 요청 보내는 흐름 확인
- [x] 로그인 상태 확인 API 만들기
- [x] 현재 로그인 사용자 정보 조회 API 만들기
- [x] 로그아웃 API 또는 로그아웃 URL 연결하기

## 11. 마무리 점검

- [ ] Google 로그인 전체 흐름 혼자 다시 구현해보기
- [ ] Kakao 로그인 전체 흐름 혼자 다시 구현해보기
- [ ] 로그인 실패 시 화면 처리하기
- [ ] 잘못된 Redirect URI일 때 어떤 에러가 나는지 확인
- [x] 민감한 키를 Git에 올리지 않도록 정리하기
- [x] 최종적으로 README에 구현 흐름 정리하기

## 추천 학습 순서

1. Spring API와 React 연결 복습
2. Spring Security 기본 로그인 흐름 이해
3. Google 로그인 먼저 구현
4. Kakao 로그인 추가
5. 사용자 정보 처리 통합
6. React 로그인 화면 연결

## 구현 흐름 요약

1. React에서 로그인 버튼을 누르면 브라우저를 `/oauth2/authorization/google` 또는 `/oauth2/authorization/kakao`로 이동시킨다.
2. Spring Security가 OAuth 로그인 과정을 시작한다.
3. Google 또는 Kakao에서 로그인에 성공하면 Spring Boot 서버의 redirect URI로 돌아온다.
4. `CustomOAuth2UserService`가 provider별 사용자 정보를 읽고 공통 사용자 정보로 변환한다.
5. `provider + providerId`로 기존 사용자를 조회하고, 없으면 저장하고 있으면 업데이트한다.
6. 인증 정보는 세션에 저장되고, 브라우저는 세션 쿠키를 들고 다닌다.
7. React는 `/api/user`를 호출해서 현재 로그인한 사용자 정보를 받아 화면에 표시한다.

## 세션 기반 로그인 흐름

1. 사용자가 로그인 요청을 보낸다.
2. Spring Security가 필터에서 요청을 가로채 인증을 수행한다.
3. 인증 성공 시 `Authentication` 객체가 `SecurityContext`에 저장된다.
4. `SecurityContext`는 세션에 저장된다.
5. 서버는 브라우저에 세션 ID 쿠키를 내려준다.
6. 이후 브라우저는 요청마다 세션 ID 쿠키를 함께 보낸다.
7. 서버는 세션 ID로 세션을 찾고, 로그인한 사용자 정보를 복원한다.
8. 복원된 사용자 정보를 기준으로 인가를 수행한다.

## 내가 어려웠던 부분 정리

- React가 `8080`으로 요청하는 이유: Spring Boot 백엔드가 `8080` 포트에서 실행되기 때문
- `fetch().then().then()` 구조: 첫 번째 `then`은 응답 객체를 읽고, 두 번째 `then`은 실제 데이터를 사용하는 단계
- `useState("loading...")`: 상태값의 초기값이라는 점
- `useEffect(..., [])`: 처음 렌더링될 때 한 번만 실행된다는 점
- `throw new Error(...)`: 정상 흐름을 중단하고 `catch`로 보내기 위해 직접 에러를 던지는 것
- Spring Security 기본 로그인 페이지가 뜨는 이유: 인증되지 않은 요청을 기본적으로 보호하기 때문
- `requestMatchers()`와 `.anyRequest().authenticated()`의 의미: 공개 경로와 인증이 필요한 나머지 경로를 나누는 설정
- `window.location.href`: OAuth 로그인처럼 브라우저 자체를 다른 주소로 이동시킬 때 사용
- Google과 Kakao의 사용자 정보 구조가 서로 달라서, 백엔드에서 공통 형식으로 매핑해줘야 React가 단순해진다는 점
- `provider + providerId`를 같이 봐야 사용자 식별이 안전하다는 점
- H2에서 `user`가 예약어라서 테이블명을 별도로 지정해야 했던 점

## 다음에 확장해볼 것

- H2 대신 MySQL 같은 실제 DB로 교체하기
- `role` 필드 추가하고 권한 처리 확장하기
- JWT 기반 로그인 방식과 세션 방식 비교해보기
- OAuth 실패 화면과 예외 처리 정리하기
