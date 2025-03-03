package com.henshi.flashcardservice.application.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateFlashcardRequest (
        @NotNull @NotEmpty String question,
        @NotNull @NotEmpty String answer
) {
}
