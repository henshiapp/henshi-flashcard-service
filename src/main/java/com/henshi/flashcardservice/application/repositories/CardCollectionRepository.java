package com.henshi.flashcardservice.application.repositories;

import com.henshi.flashcardservice.domain.models.CardCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardCollectionRepository {
    CardCollection save(CardCollection flashcard);

    Page<CardCollection> findAll(String userId, String search, Pageable pageable);

    void deleteById(String id, String userId);
}
