package com.henshi.flashcardservice.application.usecases;

import com.henshi.flashcardservice.application.dtos.requests.CreateCardCollectionRequest;
import com.henshi.flashcardservice.application.repositories.CardCollectionRepository;
import com.henshi.flashcardservice.domain.models.CardCollection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCardCollectionUseCase {

    private final CardCollectionRepository cardCollectionRepository;

    public void execute(CreateCardCollectionRequest request, String userId) {
        var collection = CardCollection.Create(
                request.title(),
                request.description(),
                request.icon(),
                userId
        );

        cardCollectionRepository.save(collection);
    }
}
