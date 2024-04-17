package org.simplon.permisdrive.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.crossstore.ChangeSetPersister;

import org.simplon.permisdrive.models.entities.Sujet;
import org.simplon.permisdrive.dtos.requests.*;
import org.simplon.permisdrive.dtos.responses.SujetResponseDto;

import java.util.List;

public interface ISujetService {

    Page<SujetResponseDto> getAllSujets(Pageable pageable);

    SujetResponseDto getSujetDtoById(Long sujetId) throws ChangeSetPersister.NotFoundException;

    Sujet getSujetById(Long sujetId) throws ChangeSetPersister.NotFoundException;

    SujetResponseDto createSujet(SujetRequestDto sujetRequestDto);

    SujetResponseDto updateSujet(Long sujetId, SujetUpdateDto sujetUpdateDto) throws ChangeSetPersister.NotFoundException;

    void deleteSujet(Long sujetId) throws ChangeSetPersister.NotFoundException;
}
