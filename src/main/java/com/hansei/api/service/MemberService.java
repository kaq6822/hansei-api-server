package com.hansei.api.service;

import com.hansei.api.dto.MemberRegistrationRequestDto;
import com.hansei.api.dto.MemberResponseDto;
import com.hansei.api.entity.Member;
import com.hansei.api.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponseDto login(String phoneNumber, String password) {
        Member member = memberRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (!member.getUserPw().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return new MemberResponseDto(member);
    }

    public MemberResponseDto find(Long userId) {
        Member member = memberRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        return new MemberResponseDto(member);
    }

    public MemberResponseDto registration(MemberRegistrationRequestDto member) {
        if (!validateMemberRegistrationRequestDto(member)) {
            throw new IllegalArgumentException("회원 정보가 올바르지 않습니다.");
        }
        Member newMember = memberRepository.save(member.toEntity());

        return new MemberResponseDto(newMember);
    }

    private boolean validateMemberRegistrationRequestDto(MemberRegistrationRequestDto member) {
        return member.phoneNumber() != null && member.userPw() != null && member.name() != null;
    }

    @Transactional
    public Long addPoint(Long memberId, Long point) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        member.addPoint(point);
        return member.getPoint();
    }
}
