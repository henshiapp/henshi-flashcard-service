package com.henshi.flashcardservice.application.repositories;

import com.henshi.flashcardservice.domain.models.Flashcard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface FlashcardRepository {
    Flashcard save(Flashcard flashcard);

    List<Flashcard> saveAll(List<Flashcard> flashcards);

    List<Flashcard> findAllByCollection(UUID collectionId);

    Page<Flashcard> findAllByCollection(UUID collectionId, String search, Pageable pageable);

    List<Flashcard> findAllAvailableForRecall(UUID collectionId);

    void deleteById(UUID id);
}
