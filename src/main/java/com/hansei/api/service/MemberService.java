package com.hansei.api.service;

import com.hansei.api.dto.MemberRegistrationRequestDto;
import com.hansei.api.dto.MemberResponseDto;
import com.hansei.api.entity.Member;
import com.hansei.api.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean memberPasswordValidation(Long userId, String password) {
        Member member = memberRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        return member.getUserPw().equals(password);
    }

    public MemberResponseDto memberInfo(Long userId) {
        Member member = memberRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        return new MemberResponseDto(member);
    }

    public MemberResponseDto createMember(MemberRegistrationRequestDto member) {
        if (!validateMemberRegistrationRequestDto(member)) {
            throw new IllegalArgumentException("회원 정보가 올바르지 않습니다.");
        }
        Member newMember = memberRepository.save(member.toEntity());

        return new MemberResponseDto(newMember);
    }

    private boolean validateMemberRegistrationRequestDto(MemberRegistrationRequestDto member) {
        return member.phoneNumber() != null && member.userPw() != null && member.name() != null;
    }
}
