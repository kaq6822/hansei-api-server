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
    @Column(name = "phone_number", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String phoneNumber;

    @Setter
    @Column(name = "user_pw", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String userPw;

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

    public Member(String phoneNumber, String userPw, String name) {
        this.phoneNumber = phoneNumber;
        this.userPw = userPw;
        this.name = name;
        this.point = 0L;
    }
}
