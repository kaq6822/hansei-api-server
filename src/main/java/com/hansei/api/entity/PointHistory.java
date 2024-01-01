package com.hansei.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Entity
@Table(name = "point_history")
@Getter
public class PointHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "point_history_id_seq")
    @SequenceGenerator(name = "point_history_id_seq", sequenceName = "point_history_id_seq", allocationSize = 1)
    @Column(name = "point_history_id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long pointHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "point", nullable = false)
    private Long point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_order_id")
    private ProductOrder productOrder;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    public PointHistory(Member member, String status, String type, Long point, ProductOrder productOrder) {
        this.member = member;
        this.status = status;
        this.type = type;
        this.point = point;
        this.productOrder = productOrder;
        this.timestamp = new Date();
    }

    public PointHistory(Member member, String status, String type, Long point) {
        this.member = member;
        this.status = status;
        this.type = type;
        this.point = point;
        this.timestamp = new Date();
    }

    public PointHistory() {
    }
}
