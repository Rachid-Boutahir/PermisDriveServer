package org.simplon.permisdrive.controllers.impl;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.crossstore.ChangeSetPersister;

import org.simplon.permisdrive.services.IAdministrateurService;
import org.simplon.permisdrive.controllers.IAdministrateurController;
import org.simplon.permisdrive.dtos.requests.AdministrateurUpdateDto;
import org.simplon.permisdrive.dtos.requests.AdministrateurRequestDto;
import org.simplon.permisdrive.dtos.responses.AdministrateurResponseDto;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/administrateur")
public class AdministrateurController implements IAdministrateurController {

    private final IAdministrateurService iAdministrateurService;
    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_SIZE = "10";
    private static final String DEFAULT_CATEGORY = "administrateurId";

    @Override
    @GetMapping("/all")
    public ResponseEntity<Page<AdministrateurResponseDto>> getAllAdministrateurs(
            @RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(name = "size", required = false, defaultValue = DEFAULT_SIZE) int size,
            @RequestParam(name = "sortByCategory", required = false, defaultValue = DEFAULT_CATEGORY) String sortByCategory,
            @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort
    ) {
        Sort.Direction direction = sort.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortByCategory));
        Page<AdministrateurResponseDto> administrateurResponseDtos = iAdministrateurService.getAllAdministrateurs(pageable);
        return ResponseEntity.ok(administrateurResponseDtos);
    }

    @Override
    @GetMapping("/{administrateurId}")
    public ResponseEntity<AdministrateurResponseDto> getAdministrateurById(
            @PathVariable(name = "administrateurId") Long administrateurId) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(iAdministrateurService.getAdministrateurDtoById(administrateurId));
    }


    @Override
    @PostMapping
    public ResponseEntity<AdministrateurResponseDto> createAdministrateur(
            @Valid @RequestBody AdministrateurRequestDto administrateurRequestDto) {
        return ResponseEntity.ok(iAdministrateurService.createAdministrateur(administrateurRequestDto));
    }

    @Override
    @PutMapping("/{administrateurId}")
    public ResponseEntity<AdministrateurResponseDto> updateAdministrateur(
            @PathVariable(name = "administrateurId") Long administrateurId,
            @Valid @RequestBody AdministrateurUpdateDto administrateurUpdateDto) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(iAdministrateurService.updateAdministrateur(administrateurId, administrateurUpdateDto));
    }

    @Override
    @DeleteMapping("/{administrateurId}")
    public ResponseEntity<String> deleteAdministrateur(
            @PathVariable(name = "administrateurId") Long administrateurId) throws ChangeSetPersister.NotFoundException {
        iAdministrateurService.deleteAdministrateur(administrateurId);
        return ResponseEntity.ok("Administrateur avec id:" + administrateurId + " a été supprimé avec succès!");
    }
}
