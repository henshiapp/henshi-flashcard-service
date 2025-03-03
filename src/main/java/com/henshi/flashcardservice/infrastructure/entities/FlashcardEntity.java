package com.henshi.flashcardservice.infrastructure.entities;

import com.henshi.flashcardservice.domain.valueobjects.Grade;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "flashcards")
public class FlashcardEntity {
    @Id
    private UUID id;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "answer", nullable = false)
    private String answer;

    @Column(name = "grade", nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(name = "next_recall", nullable = false)
    private Instant nextRecall;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Instant updatedAt;

    @Column(name = "collection_id")
    private UUID collectionId;

    @ManyToOne(targetEntity = CardCollectionEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "collection_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CardCollectionEntity collection;
}
