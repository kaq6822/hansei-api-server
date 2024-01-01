package com.hansei.api.dto;

import com.hansei.api.entity.Member;

import java.io.Serializable;

/**
 * DTO for {@link com.hansei.api.entity.Member}
 */
public record MemberRegistrationRequestDto(String phoneNumber, String userPw, String name) implements Serializable {
    public Member toEntity() {
        return new Member(phoneNumber, userPw, name);
    }
}