package com.hansei.api.dto;

public record ApiResponse<T>(String code, String message, T data) {

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>("0000", "Success", null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("0000", "Success", data);
    }

    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<>(code, message, null);
    }
}

