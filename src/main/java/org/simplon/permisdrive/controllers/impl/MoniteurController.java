package org.simplon.permisdrive.controllers.impl;

import lombok.AllArgsConstructor;
import org.simplon.permisdrive.dtos.requests.MoniteurRequestDto;
import org.simplon.permisdrive.dtos.responses.MoniteurResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

import org.simplon.permisdrive.services.IMoniteurService;
import org.simplon.permisdrive.controllers.IMoniteurController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/moniteur")
public class MoniteurController implements IMoniteurController {

    private final IMoniteurService iMoniteurService;

    @Override
    @GetMapping("all")
    public ResponseEntity<List<MoniteurResponseDto>> getAllMoniteurs() {
        return ResponseEntity.ok(iMoniteurService.getAllMoniteurs());
    }

    @Override
    @GetMapping("/{moniteurId}")
    public ResponseEntity<MoniteurResponseDto> getMoniteurById(
            @PathVariable(name = "moniteurId") Long moniteurId) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(iMoniteurService.getMoniteurDTOById(moniteurId));
    }

    @Override
    @PostMapping
    public ResponseEntity<MoniteurResponseDto> createMoniteur(
            @Valid @RequestBody MoniteurRequestDto moniteurRequestDto) {
        return ResponseEntity.ok(iMoniteurService.createMoniteur(moniteurRequestDto));
    }

    @Override
    @PutMapping("/{moniteurId}")
    public ResponseEntity<MoniteurResponseDto> updateMoniteur(
            @PathVariable(name = "moniteurId") Long moniteurId,
            @Valid @RequestBody MoniteurRequestDto moniteurRequestDto) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(iMoniteurService.updateMoniteur(moniteurId, moniteurRequestDto));
    }

    @Override
    @DeleteMapping("/{moniteurId}")
    public ResponseEntity<String> deleteMoniteur(
            @PathVariable(name = "moniteurId") Long moniteurId) throws ChangeSetPersister.NotFoundException {
        iMoniteurService.deleteMoniteur(moniteurId);
        return ResponseEntity.ok("Moniteur with id: " + moniteurId + " has been deleted successfully!");
    }



}
