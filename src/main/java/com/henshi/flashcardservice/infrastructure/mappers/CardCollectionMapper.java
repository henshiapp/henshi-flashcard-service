package com.henshi.flashcardservice.infrastructure.mappers;

import com.henshi.flashcardservice.domain.models.CardCollection;
import com.henshi.flashcardservice.infrastructure.entities.CardCollectionEntity;

public class CardCollectionMapper {
    public static CardCollection toDomain(CardCollectionEntity entity) {
        return new CardCollection(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getIcon(),
            entity.getUserId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            null
//            entity.getFlashcards() != null
//                    ? entity.getFlashcards().stream().map(FlashcardMapper::toDomain).toList()
//                    : null
        );
    }

    public static CardCollectionEntity toEntity(CardCollection domain) {
        return CardCollectionEntity.builder()
            .id(domain.getId())
            .title(domain.getTitle())
            .description(domain.getDescription())
            .icon(domain.getIcon())
            .userId(domain.getUserId())
            .createdAt(domain.getCreatedAt())
            .updatedAt(domain.getUpdatedAt())
//            .flashcards(
//                domain.getFlashcards() != null ?
//                    domain.getFlashcards().stream().map(FlashcardMapper::toEntity).toList()
//                    : null
//            )
            .build();
    }
}
