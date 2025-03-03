package com.henshi.flashcardservice.application.usecases;

import com.henshi.flashcardservice.application.dtos.requests.CreateFlashcardRequest;
import com.henshi.flashcardservice.application.repositories.FlashcardRepository;
import com.henshi.flashcardservice.domain.models.Flashcard;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateFlashcardUseCase {

    private final FlashcardRepository flashcardRepository;

    public void execute(CreateFlashcardRequest request, UUID collectionId) {
        var flashcard = Flashcard.Create(request.question(), request.answer(), collectionId);

        flashcardRepository.save(flashcard);
    }
}
