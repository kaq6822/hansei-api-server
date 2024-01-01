package com.hansei.api.repository;

import com.hansei.api.entity.Member;
import com.hansei.api.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> findByMember(Member member);
}