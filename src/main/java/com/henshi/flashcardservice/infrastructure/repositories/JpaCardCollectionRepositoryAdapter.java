package com.henshi.flashcardservice.infrastructure.repositories;

import com.henshi.flashcardservice.application.repositories.CardCollectionRepository;
import com.henshi.flashcardservice.domain.models.CardCollection;
import com.henshi.flashcardservice.infrastructure.mappers.CardCollectionMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class JpaCardCollectionRepositoryAdapter implements CardCollectionRepository {

    private final JpaCardCollectionRepository jpaCardCollectionRepository;

    @Override
    public CardCollection save(CardCollection collection) {
        return CardCollectionMapper.toDomain(jpaCardCollectionRepository.save(CardCollectionMapper.toEntity(collection)));
    }

    @Override
    public Page<CardCollection> findAll(String userId, String search, Pageable pageable) {
        if (search == null || search.isEmpty()) {
            return jpaCardCollectionRepository.findAllByUserId(userId, pageable)
                    .map(CardCollectionMapper::toDomain);
        } else {
            return jpaCardCollectionRepository.findAllByUserId(userId, search.toLowerCase(), pageable)
                    .map(CardCollectionMapper::toDomain);
        }
    }

    @Override
    public void deleteById(String id, String userId) {
        jpaCardCollectionRepository.deleteByIdAndUserId(UUID.fromString(id), userId);
    }
}
