package com.henshi.flashcardservice.application.usecases;

import com.henshi.flashcardservice.application.repositories.FlashcardRepository;
import com.henshi.flashcardservice.domain.models.Flashcard;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GetRecallFlashcardsUseCase {

    private final FlashcardRepository flashcardRepository;

    public List<Flashcard> execute(UUID collectionId) {
        return flashcardRepository.findAllAvailableForRecall(collectionId);
    }
}
