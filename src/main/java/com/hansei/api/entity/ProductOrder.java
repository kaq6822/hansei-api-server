package com.hansei.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Entity
@Table(name = "product_order")
@Getter
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", allocationSize = 1)
    @Column(name = "order_id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_member_id", nullable = false, insertable = false, updatable = false)
    private Member member;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "point_history_id", nullable = false)
    private Long pointHistoryId;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;
}