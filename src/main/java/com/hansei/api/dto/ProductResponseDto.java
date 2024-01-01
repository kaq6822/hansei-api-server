package com.hansei.api.dto;

import com.hansei.api.entity.Product;

import java.io.Serializable;

/**
 * DTO for {@link com.hansei.api.entity.Product}
 */
public record ProductResponseDto(Long productId, String productName, String productImageUrl,
                                 String productPrice) implements Serializable {
    public ProductResponseDto(Product product) {
        this(product.getProductId(), product.getProductName(), product.getProductImageUrl(), product.getProductPrice());
    }
}