package com.hansei.api.service;

import com.hansei.api.dto.MemberRegistrationRequestDto;
import com.hansei.api.dto.MemberResponseDto;
import com.hansei.api.dto.OrderRequestDto;
import com.hansei.api.dto.ProductOrderResponseDto;
import com.hansei.api.entity.Member;
import com.hansei.api.entity.PointHistory;
import com.hansei.api.entity.Product;
import com.hansei.api.entity.ProductOrder;
import com.hansei.api.repository.MemberRepository;
import com.hansei.api.repository.PointHistoryRepository;
import com.hansei.api.repository.ProductOrderRepository;
import com.hansei.api.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ProductOrderRepository productOrderRepository;
    private final PointHistoryRepository pointHistoryRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository,
                         ProductRepository productRepository,
                         ProductOrderRepository productOrderRepository,
                         PointHistoryRepository pointHistoryRepository) {
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
        this.productOrderRepository = productOrderRepository;
        this.pointHistoryRepository = pointHistoryRepository;
    }

    public MemberResponseDto login(String phoneNumber, String password) {
        Member member = memberRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (!member.getPassword().equals(password)) {
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
        return member.phoneNumber() != null && member.password() != null && member.name() != null;
    }

    @Transactional
    public Long addPoint(Long memberId, Long point) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        member.addPoint(point);

        // TODO: status, type enum으로 변경
        pointHistoryRepository.save(new PointHistory(member, "add", "charge", point));

        return member.getPoint();
    }

    public void order(Long memberId, OrderRequestDto orderRequestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Product product = productRepository.findById(orderRequestDto.productId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        if (member.getPoint() < product.getProductPrice()) {
            throw new IllegalArgumentException("포인트가 부족합니다.");
        }

        member.usePoint(product.getProductPrice());

        ProductOrder productOrder = productOrderRepository.save(new ProductOrder(product, member));
        // TODO: status, type enum으로 변경
        pointHistoryRepository.save(new PointHistory(member, "subtract", "order", product.getProductPrice(), productOrder));
    }

    public List<ProductOrderResponseDto> getOrders(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        List<ProductOrder> productOrders = productOrderRepository.findByMember(member);

        return productOrders.stream()
                .map(ProductOrderResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void cancelOrder(Long memberId, Long productOrderId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        ProductOrder productOrder = productOrderRepository.findById(productOrderId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));
        productOrder.setStatus("canceled");

        member.addPoint(productOrder.getProduct().getProductPrice());
        pointHistoryRepository.save(new PointHistory(member, "add", "cancel", productOrder.getProduct().getProductPrice(), productOrder));
    }

}