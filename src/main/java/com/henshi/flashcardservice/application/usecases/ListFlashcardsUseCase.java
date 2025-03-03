package com.henshi.flashcardservice.application.usecases;

import com.henshi.flashcardservice.application.repositories.FlashcardRepository;
import com.henshi.flashcardservice.domain.models.Flashcard;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ListFlashcardsUseCase {

    private final FlashcardRepository flashcardRepository;

    public Page<Flashcard> execute(UUID collectionId, String search, Pageable pageable) {
        return flashcardRepository.findAllByCollection(collectionId, search, pageable);
    }
}
