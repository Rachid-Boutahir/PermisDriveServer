package org.simplon.permisdrive.controllers;

import org.simplon.permisdrive.dtos.requests.SujetRequestDto;
import org.simplon.permisdrive.dtos.requests.SujetUpdateDto;
import org.simplon.permisdrive.dtos.responses.SujetResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface ISujetController {
    ResponseEntity<Page<SujetResponseDto>> getAllSujets(
            int page, int size, String sortByCategory, String sort
    );

    ResponseEntity<SujetResponseDto> getSujetById(Long sujetId) throws ChangeSetPersister.NotFoundException;

    ResponseEntity<SujetResponseDto> createSujet(SujetRequestDto sujetRequestDtoDto);

    ResponseEntity<SujetResponseDto> updateSujet(Long sujetId, SujetUpdateDto sujetUpdateDto) throws ChangeSetPersister.NotFoundException;

    ResponseEntity<String> deleteSujet(Long sujetId) throws ChangeSetPersister.NotFoundException;
}
