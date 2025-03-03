package com.henshi.flashcardservice.presentation.responses;

import org.springframework.data.domain.Page;

public record PaginationMetadata(
        Integer page,
        Long offset,
        Integer size,
        Long totalElements,
        Integer totalPages
) {

    public static PaginationMetadata from(Page page) {
        return new PaginationMetadata(
                page.getNumber(),
                page.getPageable().getOffset(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
