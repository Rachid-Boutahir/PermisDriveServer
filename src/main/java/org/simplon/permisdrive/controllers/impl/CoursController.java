package org.simplon.permisdrive.controllers.impl;

import lombok.AllArgsConstructor;
import org.simplon.permisdrive.dtos.requests.CoursRequestDto;
import org.simplon.permisdrive.dtos.requests.CoursUpdateDto;
import org.simplon.permisdrive.dtos.responses.CoursResponseDto;
import org.simplon.permisdrive.services.ICoursService;
import org.simplon.permisdrive.services.ISujetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

import org.simplon.permisdrive.controllers.ICoursController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cours")
public class CoursController implements ICoursController {

    private final ICoursService iCoursService;
    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_SIZE = "10";
    private static final String DEFAULT_CATEGORY = "sujetId";

    @Override
    @GetMapping("/all")
    public ResponseEntity<Page<CoursResponseDto>> getAllCours(
            @RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(name = "size", required = false, defaultValue = DEFAULT_SIZE) int size,
            @RequestParam(name = "sortByCategory", required = false, defaultValue = DEFAULT_CATEGORY) String sortByCategory,
            @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort
    ) {
        Sort.Direction direction = sort.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortByCategory));
        Page<CoursResponseDto> coursResponseDtos = iCoursService.getAllCours(pageable);
        return ResponseEntity.ok(coursResponseDtos);
    }

    @Override
    @GetMapping("/{coursId}")
    public ResponseEntity<CoursResponseDto> getCoursById(
            @PathVariable(name = "coursId") Long coursId) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(iCoursService.getCoursDtoById(coursId));
    }

    @Override
    @PostMapping
    public ResponseEntity<CoursResponseDto> createCours(
            @Valid @RequestBody CoursRequestDto coursRequestDto) {
        return ResponseEntity.ok(iCoursService.createCours(coursRequestDto));
    }

    @Override
    @PutMapping("/{coursId}")
    public ResponseEntity<CoursResponseDto> updateCours(
            @PathVariable(name = "coursId") Long coursId,
            @Valid @RequestBody CoursUpdateDto coursUpdateDto) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(iCoursService.updateCours(coursId, coursUpdateDto));
    }

    @Override
    @DeleteMapping("/{coursId}")
    public ResponseEntity<String> deleteCours(
            @PathVariable(name = "coursId") Long coursId) throws ChangeSetPersister.NotFoundException {
        iCoursService.deleteCours(coursId);
        return ResponseEntity.ok("Le sujet avec id: " + coursId + " a été supprimé avec succès!");
    }
}
