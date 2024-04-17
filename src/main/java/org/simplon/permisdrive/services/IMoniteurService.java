package org.simplon.permisdrive.services;

import org.simplon.permisdrive.dtos.requests.MoniteurRequestDto;
import org.simplon.permisdrive.models.entities.Moniteur;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

import org.simplon.permisdrive.dtos.responses.MoniteurResponseDto;

public interface IMoniteurService {


    List<MoniteurResponseDto> getAllMoniteurs();

    MoniteurResponseDto getMoniteurDTOById(Long moniteurId) throws ChangeSetPersister.NotFoundException;

    Moniteur getMoniteurById(Long moniteurId) throws ChangeSetPersister.NotFoundException;

    MoniteurResponseDto createMoniteur(MoniteurRequestDto moniteurRequestDto);

    MoniteurResponseDto updateMoniteur(Long moniteurId, MoniteurRequestDto moniteurRequestDto) throws ChangeSetPersister.NotFoundException;

    void deleteMoniteur(Long moniteurId) throws ChangeSetPersister.NotFoundException;
}
