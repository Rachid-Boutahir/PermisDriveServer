package org.simplon.permisdrive.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.simplon.permisdrive.dtos.requests.MoniteurRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.time.LocalDate;

import org.simplon.permisdrive.mapper.IMoniteurMapper;
import org.simplon.permisdrive.services.IMoniteurService;
import org.simplon.permisdrive.models.entities.Moniteur;
import org.simplon.permisdrive.repositories.IMoniteurRepository;
import org.simplon.permisdrive.dtos.responses.MoniteurResponseDto;

@Slf4j
@Service
@AllArgsConstructor
public class MoniteurService implements IMoniteurService {

    private final IMoniteurMapper iMoniteurMapper;
    private final IMoniteurRepository iMoniteurRepository;

    @Override
    public List<MoniteurResponseDto> getAllMoniteurs() {
        List<Moniteur> moniteurs = iMoniteurRepository.findByDeletedFalse();
        return moniteurs.stream().map(iMoniteurMapper::toResponseDto).toList();
    }

    @Override
    public MoniteurResponseDto getMoniteurDTOById(Long moniteurId) throws ChangeSetPersister.NotFoundException {
        Moniteur moniteur = iMoniteurRepository.findByMoniteurIdAndDeletedFalse(moniteurId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        return iMoniteurMapper.toResponseDto(moniteur);
    }

    @Override
    public Moniteur getMoniteurById(Long moniteurId) throws ChangeSetPersister.NotFoundException {
        return iMoniteurRepository
                .findByMoniteurIdAndDeletedFalse(moniteurId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public MoniteurResponseDto createMoniteur(MoniteurRequestDto moniteurRequestDto) {
        log.info("Creating a new moniteur request for user {} :==> {} dateTime: {}", moniteurRequestDto.getNomUtilisateur(), moniteurRequestDto.toString(), LocalDate.now());

        Moniteur moniteur = iMoniteurMapper.toEntity(moniteurRequestDto);
        System.out.println("moniteur = " + moniteur.getNom());
        moniteur.setCreatedAt(LocalDate.now());
        Moniteur moniteurEntity = iMoniteurRepository.save(moniteur);
        return iMoniteurMapper.toResponseDto(moniteurEntity);
    }

    @Override
    public MoniteurResponseDto updateMoniteur(Long moniteurId, MoniteurRequestDto moniteurRequestDto) throws ChangeSetPersister.NotFoundException {

        Moniteur existingMoniteur = iMoniteurRepository.findByMoniteurIdAndDeletedFalse(moniteurId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        Moniteur updateMoniteur = iMoniteurMapper.partialUpdate(moniteurRequestDto, existingMoniteur);

/*        existingMoniteur.setNom(moniteurDto.getNom());
        existingMoniteur.setPrenom(moniteurDto.getPrenom());
        existingMoniteur.setSexe(moniteurDto.getSexe());
        existingMoniteur.setAdresse(moniteurDto.getAdresse());
        existingMoniteur.setVille(moniteurDto.getVille());
        existingMoniteur.setTel(moniteurDto.getTel());
        existingMoniteur.setPhoto(moniteurDto.getPhoto());
        existingMoniteur.setNomUtilisateur(moniteurDto.getNomUtilisateur());
        existingMoniteur.setEmail(moniteurDto.getEmail());
        existingMoniteur.setMotsDePasse(moniteurDto.getMotsDePasse());
        existingMoniteur.setTypeMoniteur(moniteurDto.getTypeMoniteur());
        existingMoniteur.setDateRejoindre(moniteurDto.getDateRejoindre());
        existingMoniteur.setSalaire(moniteurDto.getSalaire());
        existingMoniteur.setUpdatedAt(LocalDate.now());*/

        Moniteur moniteurEntity = iMoniteurRepository.save(updateMoniteur);
        moniteurEntity.setMoniteurId(moniteurId);
        return iMoniteurMapper.toResponseDto(moniteurEntity);
    }

    @Override
    public void deleteMoniteur(Long moniteurId) throws ChangeSetPersister.NotFoundException {
        Moniteur moniteur = iMoniteurRepository.findByMoniteurIdAndDeletedFalse(moniteurId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        moniteur.setDeletedAt(LocalDate.now());
        moniteur.setDeleted(true);
        iMoniteurRepository.save(moniteur);
    }


}
