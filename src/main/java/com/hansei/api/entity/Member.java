package com.hansei.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "member")
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_id_seq")
    @SequenceGenerator(name = "member_id_seq", sequenceName = "member_id_seq", allocationSize = 1)
    @Column(name = "member_id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long memberId;

    @Setter
    @Column(name = "phone_number", nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String phoneNumber;

    @Setter
    @Column(name = "password", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String password;

    @Setter
    @Column(name = "name")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String name;

    @Setter
    @Column(name = "point", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long point;

    public Member() {
        this.point = 0L;
    }

    public Member(String phoneNumber, String password, String name) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
        this.point = 0L;
    }

    public void addPoint(Long point) {
        this.point += point;
    }

    public void usePoint(Long point) {
        this.point -= point;
    }
}
