package com.henshi.flashcardservice.presentation.rest.v1;

import com.henshi.flashcardservice.application.dtos.requests.CreateCardCollectionRequest;
import com.henshi.flashcardservice.application.dtos.requests.CreateFlashcardRequest;
import com.henshi.flashcardservice.application.dtos.requests.SaveRecallFlashcardsRequest;
import com.henshi.flashcardservice.application.usecases.*;
import com.henshi.flashcardservice.domain.models.CardCollection;
import com.henshi.flashcardservice.domain.models.Flashcard;
import com.henshi.flashcardservice.presentation.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/card-collections")
@PreAuthorize("isAuthenticated()")
public class CardCollectionController {

    private final CreateCardCollectionUseCase createCardCollectionUseCase;
    private final ListCardCollectionUseCase listCardCollectionUseCase;
    private final DeleteCardCollectionUseCase deleteCardCollectionUseCase;
    private final CreateFlashcardUseCase createFlashcardUseCase;
    private final ListFlashcardsUseCase listFlashcardsUseCase;
    private final DeleteFlashcardUseCase deleteFlashcardUseCase;
    private final GetRecallFlashcardsUseCase getRecallFlashcardsUseCase;
    private final SaveRecallFlashcardsUseCase saveRecallFlashcardsUseCase;

    @PostMapping
    public ApiResponse<Void> create(
            @RequestBody @Valid CreateCardCollectionRequest request,
            SecurityContextHolderAwareRequestWrapper requestWrapper
    ) {
        var userId = requestWrapper.getUserPrincipal().getName();

        createCardCollectionUseCase.execute(request, userId);

        return ApiResponse.success();
    }

    @GetMapping
    public ApiResponse<List<CardCollection>> listAll(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            SecurityContextHolderAwareRequestWrapper requestWrapper
    ) {
        var userId = requestWrapper.getUserPrincipal().getName();
        Pageable pageable = PageRequest.of(page, pageSize);

        var result = listCardCollectionUseCase.execute(userId, search, pageable);

        return ApiResponse.success(result);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable("id") UUID id,
            SecurityContextHolderAwareRequestWrapper requestWrapper
    ) {
        var userId = requestWrapper.getUserPrincipal().getName();

        deleteCardCollectionUseCase.execute(id.toString(), userId);

        return ApiResponse.success();
    }

    @GetMapping("/{id}/flashcards/recall")
    public ApiResponse<List<Flashcard>> getRecall(@PathVariable UUID id) {
        return ApiResponse.success(getRecallFlashcardsUseCase.execute(id));
    }

    @PostMapping("/{id}/flashcards/recall")
    public ApiResponse<Void> saveRecall(@PathVariable UUID id, @RequestBody SaveRecallFlashcardsRequest request) {
        saveRecallFlashcardsUseCase.execute(request, id);

        return ApiResponse.success();
    }

    @PostMapping("/{id}/flashcards")
    public ApiResponse<Void> createFlashcard(
            @PathVariable UUID id,
            @RequestBody @Valid CreateFlashcardRequest request
    ) {
        createFlashcardUseCase.execute(request, id);

        return ApiResponse.success();
    }

    @GetMapping("/{id}/flashcards")
    public ApiResponse<List<Flashcard>> listAllFlashcards(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Pageable pageable = PageRequest.of(page, pageSize);

        return ApiResponse.success(listFlashcardsUseCase.execute(id, search, pageable));
    }

    @DeleteMapping("/{collectionId}/flashcards/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") UUID id) {
        deleteFlashcardUseCase.execute(id);

        return ApiResponse.success();
    }
}
