package org.simplon.permisdrive.services;

import org.simplon.permisdrive.dtos.requests.AdministrateurRequestDto;
import org.simplon.permisdrive.dtos.requests.AdministrateurUpdateDto;
import org.simplon.permisdrive.dtos.responses.AdministrateurResponseDto;
import org.simplon.permisdrive.models.entities.Administrateur;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAdministrateurService {


    Page<AdministrateurResponseDto> getAllAdministrateurs(Pageable pageable);

    AdministrateurResponseDto getAdministrateurDtoById(Long administrateurId) throws ChangeSetPersister.NotFoundException;

    Administrateur getAdministrateurById(Long administrateurId) throws ChangeSetPersister.NotFoundException;

    AdministrateurResponseDto createAdministrateur(AdministrateurRequestDto administrateurResponseDto);

    AdministrateurResponseDto updateAdministrateur(Long administrateurId, AdministrateurUpdateDto administrateurUpdateDto) throws ChangeSetPersister.NotFoundException;

    void deleteAdministrateur(Long administrateurId) throws ChangeSetPersister.NotFoundException;

}
