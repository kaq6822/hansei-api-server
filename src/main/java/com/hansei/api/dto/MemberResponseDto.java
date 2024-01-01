package com.hansei.api.dto;

import com.hansei.api.entity.Member;

import java.io.Serializable;

/**
 * DTO for {@link com.hansei.api.entity.Member}
 */
public record MemberResponseDto(Long memberId, String phoneNumber, String password, String name,
                                Long point) implements Serializable {
    public MemberResponseDto(Member member) {
        this(member.getMemberId(), member.getPhoneNumber(), member.getPassword(), member.getName(),
                member.getPoint());
    }
}