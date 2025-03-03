package com.henshi.flashcardservice.application.dtos.requests;

import java.util.UUID;

public record RecallFlashcardsResult(UUID flashcardId, Boolean correct) {}
