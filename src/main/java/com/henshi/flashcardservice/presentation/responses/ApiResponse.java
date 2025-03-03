package com.henshi.flashcardservice.presentation.responses;

import org.springframework.data.domain.Page;

import java.util.List;

public record ApiResponse<T>(String status, String message, T data, PaginationMetadata metadata, List<ValidationError> errors) {
    public static ApiResponse<Void> success() {
        return new ApiResponse<>("success", null, null, null, null);
    }

    public static <U> ApiResponse<U> success(U data) {
        return new ApiResponse<>("success", null, data, null, null);
    }

    public static <U> ApiResponse<List<U>> success(Page<U> data) {
        return new ApiResponse<>("success", null, data.getContent(), PaginationMetadata.from(data), null);
    }

    public static ApiResponse<Void> error(String message, List<ValidationError> errors) {
        return new ApiResponse<>("error", message, null, null, errors);
    }
}
