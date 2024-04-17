package org.simplon.permisdrive.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.time.LocalDate;

import org.simplon.permisdrive.models.entities.Sujet;
import org.simplon.permisdrive.mapper.ISujetMapper;
import org.simplon.permisdrive.services.ISujetService;
import org.simplon.permisdrive.repositories.ISujetRepository;
import org.simplon.permisdrive.dtos.requests.SujetRequestDto;
import org.simplon.permisdrive.dtos.requests.SujetUpdateDto;
import org.simplon.permisdrive.dtos.responses.SujetResponseDto;

@Slf4j
@Service
@AllArgsConstructor
public class SujetService implements ISujetService {

    private final ISujetRepository iSujetRepository;
    private final ISujetMapper iSujetMapper;

    @Override
    public Page<SujetResponseDto> getAllSujets(Pageable pageable) {
        log.info("Récupération de l'ensemble des sujets . total est: {}", iSujetRepository.count());
        Page<Sujet> sujets = iSujetRepository.findAll(pageable);
        return sujets.map(iSujetMapper::toResponseDto);
    }

    @Override
    public SujetResponseDto getSujetDtoById(Long sujetId) throws ChangeSetPersister.NotFoundException {
        log.info("Récupération de objet 'sujet' avec identifiant: {}", sujetId);
        log.info("Sujet :[ {} ]", iSujetRepository.findById(sujetId));
        Sujet sujet = iSujetRepository.findById(sujetId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        return iSujetMapper.toResponseDto(sujet);
    }

    @Override
    public Sujet getSujetById(Long sujetId) throws ChangeSetPersister.NotFoundException {
        log.info("Récupération de objet 'sujet' avec identifiant: {}", sujetId);
        log.info("Sujet :[ {} ]", iSujetRepository.findById(sujetId));
        return iSujetRepository.findById(sujetId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public SujetResponseDto createSujet(SujetRequestDto sujetRequestDto) {
        log.info("Date d'Ajout :{} -- Ajouter un nouveau objet 'Sujet'. {} :Objet:[{}]", LocalDate.now(), sujetRequestDto.getSujet(), sujetRequestDto.toString());
        Sujet sujet = iSujetMapper.toEntity(sujetRequestDto);
        sujet.setCreatedAt(LocalDate.now());
        iSujetRepository.save(sujet);
        return iSujetMapper.toResponseDto(sujet);
    }

    @Override
    public SujetResponseDto updateSujet(Long sujetId, SujetUpdateDto sujetUpdateDto) throws ChangeSetPersister.NotFoundException {
        log.info("Date de mise à jour :{} -- Mettre à jour l'objet 'Sujet' {} :Objet:[{}]", LocalDate.now(), sujetUpdateDto.getSujet(), sujetUpdateDto.toString());
        Sujet sujet = iSujetRepository.findById(sujetId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        Sujet sujetUpdated = iSujetMapper.partialUpdate(sujetUpdateDto, sujet);
        sujetUpdated.setUpdatedAt(LocalDate.now());
        Sujet sujetEtity = iSujetRepository.save(sujetUpdated);
        sujetEtity.setSujetId(sujetId);
        return iSujetMapper.toResponseDto(sujetEtity);
    }

    @Override
    public void deleteSujet(Long sujetId) throws ChangeSetPersister.NotFoundException {
        log.info("Date de suppression :{} -- l'objet 'Sujet' avec identifiant ID: {} a été supprimer", LocalDate.now(), sujetId);
        Sujet sujet = iSujetRepository.findById(sujetId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        iSujetRepository.delete(sujet);
    }
}
