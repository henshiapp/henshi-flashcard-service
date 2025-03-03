package com.henshi.flashcardservice.application.dtos.requests;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record CreateCardCollectionRequest(
        @NotBlank String title,
        @Nullable String description,
        @NotBlank String icon
) {
}
