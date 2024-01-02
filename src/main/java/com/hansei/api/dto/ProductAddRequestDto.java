package com.hansei.api.dto;

import com.hansei.api.entity.Product;

import java.io.Serializable;

/**
 * DTO for {@link com.hansei.api.entity.Product}
 */
public record ProductAddRequestDto(String productName, String productImageUrl,
                                   Long productPrice) implements Serializable {
    public Product toEntity() {
        return new Product(productName, productImageUrl, productPrice);
    }
}