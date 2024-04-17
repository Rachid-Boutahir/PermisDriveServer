package org.simplon.permisdrive.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.time.LocalDate;

import org.simplon.permisdrive.mapper.ICoursMapper;
import org.simplon.permisdrive.models.entities.Cours;
import org.simplon.permisdrive.services.ICoursService;
import org.simplon.permisdrive.repositories.ICoursRepository;
import org.simplon.permisdrive.dtos.requests.CoursRequestDto;
import org.simplon.permisdrive.dtos.requests.CoursUpdateDto;
import org.simplon.permisdrive.dtos.responses.CoursResponseDto;

@Slf4j
@Service
@AllArgsConstructor
public class CoursService implements ICoursService {

    private final ICoursRepository iCoursRepository;
    private final ICoursMapper iCoursMapper;

    @Override
    public Page<CoursResponseDto> getAllCours(Pageable pageable) {
        log.info("Récupération de l'ensemble des cours . total est: {}", iCoursRepository.count());
        Page<Cours> cours = iCoursRepository.findAll(pageable);
        return cours.map(iCoursMapper::toResponseDto);
    }

    @Override
    public CoursResponseDto getCoursDtoById(Long coursId) throws ChangeSetPersister.NotFoundException {
        log.info("Récupération de objet 'cours' avec identifiant: {}", coursId);
        log.info("Cours :[ {} ]", iCoursRepository.findById(coursId));
        Cours cours = iCoursRepository.findById(coursId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        return iCoursMapper.toResponseDto(cours);
    }

    @Override
    public Cours getCoursById(Long coursId) throws ChangeSetPersister.NotFoundException {
        log.info("Récupération de objet 'cours' avec identifiant: {}", coursId);
        log.info("Cours :[ {} ]", iCoursRepository.findById(coursId));
        return iCoursRepository.findById(coursId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public CoursResponseDto createCours(CoursRequestDto coursRequestDto) {
        log.info("Date d'Ajout :{} -- Ajouter un nouveau objet 'Cours'. :Objet:[{}]", LocalDate.now(), coursRequestDto.toString());
        Cours cours = iCoursMapper.RequestDtoToEntity(coursRequestDto);
        cours.setCreatedAt(LocalDate.now());
        iCoursRepository.save(cours);
        return iCoursMapper.toResponseDto(cours);
    }

    @Override
    public CoursResponseDto updateCours(Long coursId, CoursUpdateDto coursUpdateDto) throws ChangeSetPersister.NotFoundException {
        log.info("Date de modification :{} -- Modification de l'objet 'Cours'. :Objet:[{}]", LocalDate.now(), coursUpdateDto.toString());
        Cours cours = iCoursRepository.findById(coursId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        Cours coursUpdated = iCoursMapper.partialUpdate(coursUpdateDto, cours);
        coursUpdated.setUpdatedAt(LocalDate.now());
        Cours coursEtity = iCoursRepository.save(coursUpdated);
        coursEtity.setCoursId(coursId);
        return iCoursMapper.toResponseDto(coursEtity);
    }


    @Override
    public void deleteCours(Long coursId) throws ChangeSetPersister.NotFoundException {
        log.info("Date de suppression :{} -- l'objet 'Cours' avec identifiant ID: {} a été supprimer", LocalDate.now(), coursId);
        Cours existingCours = iCoursRepository.findById(coursId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        iCoursRepository.delete(existingCours);
    }
}
