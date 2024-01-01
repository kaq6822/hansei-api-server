package com.hansei.api.dto;

import com.hansei.api.entity.ProductOrder;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.hansei.api.entity.ProductOrder}
 */
public record ProductOrderResponseDto(Long orderId, Long productProductId, Long memberMemberId, String status,
                                      Long price,
                                      Date timestamp) implements Serializable {
    public ProductOrderResponseDto(ProductOrder productOrder) {
        this(productOrder.getOrderId(), productOrder.getProduct().getProductId(),
                productOrder.getMember().getMemberId(), productOrder.getStatus(), productOrder.getPrice(),
                productOrder.getTimestamp());
    }
}