package com.henshi.flashcardservice.application.dtos.requests;

import java.util.List;

public record SaveRecallFlashcardsRequest(
        List<RecallFlashcardsResult> answers
) {
}

