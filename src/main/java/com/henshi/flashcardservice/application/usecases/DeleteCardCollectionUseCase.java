package com.henshi.flashcardservice.application.usecases;

import com.henshi.flashcardservice.application.repositories.CardCollectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteCardCollectionUseCase {

    private final CardCollectionRepository cardCollectionRepository;

    public void execute(String collectionId, String userId) {
        cardCollectionRepository.deleteById(collectionId, userId);
    }
}
