package com.example.demo.domain.user;

// 소셜 로그인 사용자가 이미 가입된 사람인지 찾는 것

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


// public interface UserRepository
// 클래스가 아니라 인터페이스, Spring Data JPA가 자동으로 구현해줌
// User, Long: User 엔티티 대상으로 기본 키 타입은 Long, JPA 저장/조회 기능을 사용하겠다.
public interface UserRepository extends JpaRepository<User, Long> {
    // provider와 providerId로 사용자를 찾는다. 결과가 없을 수도 있기 때문에 Optional<User>로 감싼다.
    Optional<User> findByProviderAndProviderId(String provider, String providerId);
}
 