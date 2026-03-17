package com.example.demo.domain.user;

// 사용자 정보를 DB에 저장하기 위한 엔티티 클래스
// 엔티티란? 데이터베이스 테이블과 1:1로 매핑되는 자바 객체
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// JPA가 관리하는 엔티티: DB 테이블과 연결되는 클래스
@Entity
@Table(name = "users")
public class User {

    // id: 기본 키
    // GeneratedValue: 값 자동 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider;
    private String providerId;
    private String name;
    private String picture;

    // JPA가 객체를 만들 때 필요한 기본 생성자
    protected User() {
    }

    public User(String provider, String providerId, String name, String picture) {
        this.provider = provider;
        this.providerId = providerId;
        this.name = name;
        this.picture = picture;
    }

    // void: 아무것도 반환하지 않는다.
    public void update(String name, String picture) {
        this.name = name;
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public String getProvider() {
        return provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }
}
