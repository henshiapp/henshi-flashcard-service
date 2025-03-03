package com.henshi.flashcardservice.infrastructure.mappers;

import com.henshi.flashcardservice.domain.models.Flashcard;
import com.henshi.flashcardservice.infrastructure.entities.FlashcardEntity;

public class FlashcardMapper {
    public static Flashcard toDomain(FlashcardEntity entity) {
        return new Flashcard(
                entity.getId(),
                entity.getQuestion(),
                entity.getAnswer(),
                entity.getGrade(),
                entity.getNextRecall(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getCollectionId()
        );
    }

    public static FlashcardEntity toEntity(Flashcard domain) {
        return FlashcardEntity.builder()
                .id(domain.getId())
                .question(domain.getQuestion())
                .answer(domain.getAnswer())
                .grade(domain.getGrade())
                .nextRecall(domain.getNextRecall())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .collectionId(domain.getCollectionId())
                .build();
    }
}
