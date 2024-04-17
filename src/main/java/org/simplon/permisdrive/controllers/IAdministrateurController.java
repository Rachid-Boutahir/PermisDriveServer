package org.simplon.permisdrive.controllers;

import org.simplon.permisdrive.dtos.requests.AdministrateurRequestDto;
import org.simplon.permisdrive.dtos.requests.AdministrateurUpdateDto;
import org.simplon.permisdrive.dtos.responses.AdministrateurResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface IAdministrateurController {
    ResponseEntity<Page<AdministrateurResponseDto>> getAllAdministrateurs(int page, int size, String sortByCategory, String sort);

    ResponseEntity<AdministrateurResponseDto> getAdministrateurById(Long administrateurId) throws ChangeSetPersister.NotFoundException;

    ResponseEntity<AdministrateurResponseDto> createAdministrateur(AdministrateurRequestDto administrateurRequestDto);

    ResponseEntity<AdministrateurResponseDto> updateAdministrateur(Long administrateurId, AdministrateurUpdateDto administrateurUpdateDto) throws ChangeSetPersister.NotFoundException;

    ResponseEntity<String> deleteAdministrateur(Long administrateurId) throws ChangeSetPersister.NotFoundException;
}
