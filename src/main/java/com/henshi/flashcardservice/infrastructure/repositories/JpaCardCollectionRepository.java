package com.henshi.flashcardservice.infrastructure.repositories;

import com.henshi.flashcardservice.infrastructure.entities.CardCollectionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaCardCollectionRepository extends JpaRepository<CardCollectionEntity, UUID> {

    Page<CardCollectionEntity> findAllByUserId(String userId, Pageable pageable);

    @Query("SELECT c FROM card_collections c " +
            "LEFT JOIN FETCH c.flashcards " +
            "WHERE c.userId = :userId AND " +
            "(c.title LIKE CONCAT('%', :search, '%') OR c.description LIKE CONCAT('%', :search, '%'))")
    Page<CardCollectionEntity> findAllByUserId(String userId, String search, Pageable pageable);

    @Transactional
    void deleteByIdAndUserId(UUID id, String userId);
}
