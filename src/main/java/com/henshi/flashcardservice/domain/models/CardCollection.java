package com.henshi.flashcardservice.domain.models;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class CardCollection {
    private UUID id;

    private String title;

    @Nullable
    private String description;

    private String icon;

    private String userId;

    private Instant createdAt;

    private Instant updatedAt;

    @Builder.Default
    private List<Flashcard> flashcards = new ArrayList<>();

    public static CardCollection Create(String title, String description, String icon, String userId) {
        return CardCollection.builder()
            .id(UUID.randomUUID())
            .title(title)
            .description(description)
            .icon(icon)
            .userId(userId)
            .createdAt(Instant.now())
            .updatedAt(Instant.now())
            .build();
    }
}
