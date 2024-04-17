package org.simplon.permisdrive.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.time.LocalDate;
import java.util.List;

import org.simplon.permisdrive.mapper.IAdministrateurMapper;
import org.simplon.permisdrive.models.entities.Administrateur;
import org.simplon.permisdrive.services.IAdministrateurService;
import org.simplon.permisdrive.repositories.IAdministrateurRepository;
import org.simplon.permisdrive.dtos.requests.AdministrateurUpdateDto;
import org.simplon.permisdrive.dtos.requests.AdministrateurRequestDto;
import org.simplon.permisdrive.dtos.responses.AdministrateurResponseDto;

@Slf4j
@Service
@AllArgsConstructor
public class AdministrateurService implements IAdministrateurService {

    private final IAdministrateurRepository iAdministrateurRepository;
    private final IAdministrateurMapper iAdministrateurMapper;

    @Override
    public Page<AdministrateurResponseDto> getAllAdministrateurs(Pageable pageable) {
        log.info("Récupération de l'ensemble des administrateurs. numbre est: {}", iAdministrateurRepository.countByDeletedFalse());
        Page<Administrateur> administrateurs = iAdministrateurRepository.findByDeletedFalse(pageable);
        return administrateurs.map(iAdministrateurMapper::toResponseDto);
    }

    @Override
    public AdministrateurResponseDto getAdministrateurDtoById(Long administrateurId) throws ChangeSetPersister.NotFoundException {
        log.info("Récupération de l'administrateur avec identifiant: {}", administrateurId);
        log.info("Administrateur : {}", iAdministrateurRepository.findById(administrateurId));

        Administrateur administrateur = iAdministrateurRepository.findByAdministrateurIdAndDeletedFalse(administrateurId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        return iAdministrateurMapper.toResponseDto(administrateur);
    }

    @Override
    public Administrateur getAdministrateurById(Long administrateurId) throws ChangeSetPersister.NotFoundException {
        log.info("Récupération de l'administrateur avec identifiant: {}", administrateurId);
        log.info("Administrateur : {}", iAdministrateurRepository.findById(administrateurId));
        return iAdministrateurRepository
                .findByAdministrateurIdAndDeletedFalse(administrateurId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public AdministrateurResponseDto createAdministrateur(AdministrateurRequestDto administrateurRequestDto) {
        log.info("Date de creation :{} -- Créer un nouveau administrateur. {} :Objet:[{}]", LocalDate.now(), administrateurRequestDto.getNom(), administrateurRequestDto.toString());

        Administrateur administrateur = iAdministrateurMapper.toEntity(administrateurRequestDto);
        administrateur.setCreatedAt(LocalDate.now());
        Administrateur administrateurEntity = iAdministrateurRepository.save(administrateur);
        return iAdministrateurMapper.toResponseDto(administrateurEntity);
    }

    @Override
    public AdministrateurResponseDto updateAdministrateur(Long administrateurId, AdministrateurUpdateDto administrateurUpdateDto) throws ChangeSetPersister.NotFoundException {
        log.info("Date de mise à jour :{} -- mettre à jour l'administrateur. {} :Objet:[{}]", LocalDate.now(), administrateurUpdateDto.getNom(), administrateurUpdateDto.toString());

        Administrateur existingAdministrateur = iAdministrateurRepository.findByAdministrateurIdAndDeletedFalse(administrateurId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        Administrateur updateAdministrateur = iAdministrateurMapper.partialUpdate(administrateurUpdateDto, existingAdministrateur);

//        existingAdministrateur.setNom(administrateurRequestDto.getNom());
//        existingAdministrateur.setPrenom(administrateurRequestDto.getPrenom());
//        existingAdministrateur.setSexe(administrateurRequestDto.getSexe());
//        existingAdministrateur.setAdresse(administrateurRequestDto.getAdresse());
//        existingAdministrateur.setVille(administrateurRequestDto.getVille());
//        existingAdministrateur.setPhoto(administrateurRequestDto.getPhoto());
//        existingAdministrateur.setNomUtilisateur(administrateurRequestDto.getNomUtilisateur());
//        existingAdministrateur.setEmail(administrateurRequestDto.getEmail());
//        existingAdministrateur.setMotsDePasse(administrateurRequestDto.getMotsDePasse());
//        existingAdministrateur.setTelephone(administrateurRequestDto.getTelephone());
        updateAdministrateur.setUpdatedAt(LocalDate.now());

        Administrateur administrateurEntity = iAdministrateurRepository.save(updateAdministrateur);
        administrateurEntity.setAdministrateurId(administrateurId);
        return iAdministrateurMapper.toResponseDto(administrateurEntity);
    }

    @Override
    public void deleteAdministrateur(Long administrateurId) throws ChangeSetPersister.NotFoundException {
        log.info("Date de suppression :{} -- l'administrateur avec ID:{}. a été supprimer", LocalDate.now(), administrateurId);
        Administrateur administrateur = iAdministrateurRepository.findByAdministrateurIdAndDeletedFalse(administrateurId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        administrateur.setDeleted(true);
        iAdministrateurRepository.save(administrateur);
    }
}
