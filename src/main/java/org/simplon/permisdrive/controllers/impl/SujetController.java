package org.simplon.permisdrive.controllers.impl;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.data.crossstore.ChangeSetPersister;

import org.simplon.permisdrive.services.ISujetService;
import org.simplon.permisdrive.dtos.requests.SujetRequestDto;
import org.simplon.permisdrive.dtos.requests.SujetUpdateDto;
import org.simplon.permisdrive.dtos.responses.SujetResponseDto;
import org.simplon.permisdrive.controllers.ISujetController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/sujet")
public class SujetController implements ISujetController {

    private final ISujetService iSujetService;
    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_SIZE = "10";
    private static final String DEFAULT_CATEGORY = "sujetId";

    @Override
    @GetMapping("/all")
    public ResponseEntity<Page<SujetResponseDto>> getAllSujets(
            @RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(name = "size", required = false, defaultValue = DEFAULT_SIZE) int size,
            @RequestParam(name = "sortByCategory", required = false, defaultValue = DEFAULT_CATEGORY) String sortByCategory,
            @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort
    ) {
        Sort.Direction direction = sort.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortByCategory));
        Page<SujetResponseDto> sujetResponseDtos = iSujetService.getAllSujets(pageable);
        return ResponseEntity.ok(sujetResponseDtos);
    }

    @Override
    @GetMapping("/{sujetId}")
    public ResponseEntity<SujetResponseDto> getSujetById(
            @PathVariable(name = "sujetId") Long sujetId) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(iSujetService.getSujetDtoById(sujetId));
    }

    @Override
    @PostMapping
    public ResponseEntity<SujetResponseDto> createSujet(
            @Valid @RequestBody SujetRequestDto sujetRequestDtoDto) {
        return ResponseEntity.ok(iSujetService.createSujet(sujetRequestDtoDto));
    }

    @Override
    @PutMapping("/{sujetId}")
    public ResponseEntity<SujetResponseDto> updateSujet(
            @PathVariable(name = "sujetId") Long sujetId,
            @Valid @RequestBody SujetUpdateDto sujetUpdateDto) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(iSujetService.updateSujet(sujetId, sujetUpdateDto));
    }

    @Override
    @DeleteMapping("/{sujetId}")
    public ResponseEntity<String> deleteSujet(
            @PathVariable(name = "sujetId") Long sujetId) throws ChangeSetPersister.NotFoundException {
        iSujetService.deleteSujet(sujetId);
        return ResponseEntity.ok("Le sujet avec id: " + sujetId + " a été supprimé avec succès!");
    }
}
