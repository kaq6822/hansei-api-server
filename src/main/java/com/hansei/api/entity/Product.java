package com.hansei.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "product")
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    @Column(name = "product_id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long productId;

    @Column(name = "product_name", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String productName;

    @Column(name = "product_image_url", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String productImageUrl;

    @Column(name = "product_price", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long productPrice;

    public Product(String productName, String productImageUrl, Long productPrice) {
        this.productName = productName;
        this.productImageUrl = productImageUrl;
        this.productPrice = productPrice;
    }

    public Product() {
    }
}
