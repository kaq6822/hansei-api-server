package com.hansei.api.controller;


import com.hansei.api.dto.ApiResponse;
import com.hansei.api.dto.MemberLoginRequestDto;
import com.hansei.api.dto.MemberResponseDto;
import com.hansei.api.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ApiResponse<MemberResponseDto> login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        return ApiResponse.success(memberService.login(memberLoginRequestDto.phoneNumber(), memberLoginRequestDto.userPw()));
    }
}
