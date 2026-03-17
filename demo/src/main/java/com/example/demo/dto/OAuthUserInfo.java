package com.example.demo.dto;

// 구글/카카오 응답을 공통 형식으로 담는 DTO
// 잠깐 데이터 가공용으로 쓰는 객체
public class OAuthUserInfo {

    private final String provider;
    private final String providerId;
    private final String name;
    private final String picture;

    public OAuthUserInfo(String provider, String providerId, String name, String picture) {
        this.provider = provider;
        this.providerId = providerId;
        this.name = name;
        this.picture = picture;
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

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
