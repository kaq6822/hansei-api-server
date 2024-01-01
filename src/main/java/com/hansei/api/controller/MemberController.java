package com.hansei.api.controller;


import com.hansei.api.dto.*;
import com.hansei.api.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ApiResponse<MemberResponseDto> login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        return ApiResponse.success(memberService.login(memberLoginRequestDto.phoneNumber(), memberLoginRequestDto.password()));
    }

    @PostMapping("/member")
    public ApiResponse<MemberResponseDto> registration(@RequestBody MemberRegistrationRequestDto memberRegistrationRequestDto) {
        return ApiResponse.success(memberService.registration(memberRegistrationRequestDto));
    }

    @GetMapping("/member/{memberId}/point")
    public ApiResponse<Long> getPoint(@PathVariable Long memberId) {
        return ApiResponse.success(memberService.find(memberId).point());
    }

    @PostMapping("/member/{memberId}/point")
    public ApiResponse<Long> addPoint(@PathVariable Long memberId, @RequestBody MemberAddPointRequestDto memberAddPointRequestDto) {
        return ApiResponse.success(memberService.addPoint(memberId, memberAddPointRequestDto.point()));
    }

    @PostMapping("/member/{memberId}/order")
    public ApiResponse order(@PathVariable Long memberId, @RequestBody OrderRequestDto orderRequestDto) {
        memberService.order(memberId, orderRequestDto);
        return ApiResponse.success();
    }

    @GetMapping("/member/{memberId}/orders")
    public ApiResponse<List<ProductOrderResponseDto>> getOrders(@PathVariable Long memberId) {
        return ApiResponse.success(memberService.getOrders(memberId));
    }

    @DeleteMapping("/member/{memberId}/order/{orderId}")
    public ApiResponse cancelOrder(@PathVariable Long memberId, @PathVariable Long orderId) {
        memberService.cancelOrder(memberId, orderId);
        return ApiResponse.success();
    }
}
