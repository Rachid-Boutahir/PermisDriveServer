package org.simplon.permisdrive.controllers;

import org.simplon.permisdrive.dtos.requests.MoniteurRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

import org.simplon.permisdrive.dtos.responses.MoniteurResponseDto;

public interface IMoniteurController {

    ResponseEntity<List<MoniteurResponseDto>> getAllMoniteurs();

    ResponseEntity<MoniteurResponseDto> getMoniteurById(Long moniteurId) throws ChangeSetPersister.NotFoundException;

    ResponseEntity<MoniteurResponseDto> createMoniteur(MoniteurRequestDto moniteur);

    ResponseEntity<MoniteurResponseDto> updateMoniteur(Long moniteurId, MoniteurRequestDto moniteurDto) throws ChangeSetPersister.NotFoundException;

    ResponseEntity<String> deleteMoniteur(Long moniteurId) throws ChangeSetPersister.NotFoundException;
}
