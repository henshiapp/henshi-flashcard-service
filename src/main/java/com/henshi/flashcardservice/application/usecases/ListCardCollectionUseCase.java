package com.henshi.flashcardservice.application.usecases;

import com.henshi.flashcardservice.application.repositories.CardCollectionRepository;
import com.henshi.flashcardservice.domain.models.CardCollection;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListCardCollectionUseCase {

    private final CardCollectionRepository cardCollectionRepository;

    public Page<CardCollection> execute(String userId, String search, Pageable pageable) {
        return cardCollectionRepository.findAll(userId, search, pageable);
    }
}
