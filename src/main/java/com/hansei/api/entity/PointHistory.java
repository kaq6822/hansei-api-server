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
    @JoinColumn(name = "member_id", nullable = false, insertable = false, updatable = false)
    private Member member;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "point", nullable = false)
    private Long point;

    @Column(name = "refunded_point", nullable = false)
    private Long refundedPoint;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;
}
