package com.henshi.flashcardservice.infrastructure.repositories;

import com.henshi.flashcardservice.infrastructure.entities.FlashcardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaFlashcardRepository extends JpaRepository<FlashcardEntity, UUID> {

    List<FlashcardEntity> findAllByCollectionId(UUID collectionId);

    Page<FlashcardEntity> findAllByCollectionId(UUID collectionId, Pageable pageable);

    @Query("SELECT f FROM flashcards f " +
            "WHERE f.collectionId = :collectionId AND " +
            "(f.question LIKE CONCAT('%', :search, '%') OR f.answer LIKE CONCAT('%', :search, '%'))")
    Page<FlashcardEntity> findAllByCollectionId(UUID collectionId, String search, Pageable pageable);

    @Query("SELECT f FROM flashcards f WHERE f.collectionId = :collectionId AND f.nextRecall <= CURRENT_TIMESTAMP")
    List<FlashcardEntity> findAllAvailableForRecall(UUID collectionId);
}
