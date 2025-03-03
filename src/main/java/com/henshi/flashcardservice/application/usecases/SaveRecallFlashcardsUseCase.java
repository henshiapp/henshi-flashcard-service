package com.henshi.flashcardservice.application.usecases;

import com.henshi.flashcardservice.application.dtos.requests.SaveRecallFlashcardsRequest;
import com.henshi.flashcardservice.application.repositories.FlashcardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SaveRecallFlashcardsUseCase {

    private final FlashcardRepository flashcardRepository;

    public void execute(SaveRecallFlashcardsRequest request, UUID collectionId) {
        var flashcards = flashcardRepository.findAllByCollection(collectionId);

        request.answers().forEach(answer -> {
            var flashcard = flashcards.stream().filter(f -> f.getId().equals(answer.flashcardId())).findFirst();

            if (flashcard.isEmpty()) return;

            if (answer.correct()) {
                flashcard.get().advanceToNextGrade();
            } else {
                flashcard.get().returnToLastGrade();
            }
        });

        flashcardRepository.saveAll(flashcards);
    }
}
