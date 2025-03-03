package com.henshi.flashcardservice.domain.models;

import com.henshi.flashcardservice.domain.valueobjects.Grade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class Flashcard {
    private UUID id;

    private String question;

    private String answer;

    private Grade grade;

    private Instant nextRecall;

    private Instant createdAt;

    private Instant updatedAt;

    private UUID collectionId;

    public static Flashcard Create(String question, String answer, UUID colletionId) {
        return Flashcard.builder()
                .id(UUID.randomUUID())
                .question(question)
                .answer(answer)
                .grade(Grade.VERY_HARD)
                .nextRecall(Instant.now())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .collectionId(colletionId)
                .build();
    }

    public void returnToLastGrade() {
        switch (grade) {
            case VERY_HARD: {
                break;
            }
            case HARD: {
                grade = Grade.VERY_HARD;
                break;
            }
            case MEDIUM: {
                grade = Grade.HARD;
                break;
            }
            case EASY: {
                grade = Grade.MEDIUM;
                break;
            }
            case VERY_EASY: {
                grade = Grade.EASY;
                break;
            }
        }
        nextRecall = calculateNextRecall();
    }

    public void advanceToNextGrade() {
        switch (grade) {
            case VERY_HARD: {
                grade = Grade.HARD;
                break;
            }
            case HARD: {
                grade = Grade.MEDIUM;
                break;
            }
            case MEDIUM: {
                grade = Grade.EASY;
                break;
            }
            case EASY: {
                grade = Grade.VERY_EASY;
                break;
            }
            case VERY_EASY: {
                break;
            }
        }
        nextRecall = calculateNextRecall();
    }

    public Instant calculateNextRecall() {
        return switch (grade) {
            case VERY_EASY -> ZonedDateTime.now().plusDays(30).toInstant();
            case EASY -> ZonedDateTime.now().plusDays(14).toInstant();
            case MEDIUM -> ZonedDateTime.now().plusDays(7).toInstant();
            case HARD -> ZonedDateTime.now().plusDays(3).toInstant();
            case VERY_HARD -> ZonedDateTime.now().plusDays(1).toInstant();
        };
    }
}
