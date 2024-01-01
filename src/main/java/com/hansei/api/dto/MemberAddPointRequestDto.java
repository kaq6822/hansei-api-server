package com.hansei.api.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.hansei.api.entity.Member}
 */
public record MemberAddPointRequestDto(Long point) implements Serializable {
}