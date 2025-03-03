package com.henshi.flashcardservice.infrastructure.repositories;

import com.henshi.flashcardservice.application.repositories.FlashcardRepository;
import com.henshi.flashcardservice.domain.models.Flashcard;
import com.henshi.flashcardservice.infrastructure.mappers.FlashcardMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class JpaFlashcardRepositoryAdapter implements FlashcardRepository {

    private final JpaFlashcardRepository jpaFlashcardRepository;

    @Override
    public Flashcard save(Flashcard flashcard) {
        return FlashcardMapper.toDomain(jpaFlashcardRepository.save(FlashcardMapper.toEntity(flashcard)));
    }

    @Override
    @Transactional
    public List<Flashcard> saveAll(List<Flashcard> flashcards) {
        return jpaFlashcardRepository
                .saveAll(flashcards.stream().map(FlashcardMapper::toEntity).toList()).stream()
                .map(FlashcardMapper::toDomain)
                .toList();
    }

    public List<Flashcard> findAllByCollection(UUID collectionId) {
        return jpaFlashcardRepository.findAllByCollectionId(collectionId).stream()
                .map(FlashcardMapper::toDomain)
                .toList();
    }

    @Override
    public Page<Flashcard> findAllByCollection(UUID collectionId, String search, Pageable pageable) {
        if (search == null || search.isEmpty()) {
            return jpaFlashcardRepository.findAllByCollectionId(collectionId, pageable)
                    .map(FlashcardMapper::toDomain);
        } else {
            return jpaFlashcardRepository.findAllByCollectionId(collectionId, search, pageable)
                    .map(FlashcardMapper::toDomain);
        }
    }

    @Override
    public List<Flashcard> findAllAvailableForRecall(UUID collectionId) {
        return jpaFlashcardRepository.findAllAvailableForRecall(collectionId).stream().map(FlashcardMapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaFlashcardRepository.deleteById(id);
    }
}
