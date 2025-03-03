package com.henshi.flashcardservice.application.usecases;

import com.henshi.flashcardservice.application.repositories.FlashcardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DeleteFlashcardUseCase {

    private final FlashcardRepository flashcardRepository;

    public void execute(UUID id) {
        flashcardRepository.deleteById(id);
    }
}
