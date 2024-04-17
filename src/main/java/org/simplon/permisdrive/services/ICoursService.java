package org.simplon.permisdrive.services;

import org.simplon.permisdrive.dtos.requests.CoursRequestDto;
import org.simplon.permisdrive.dtos.requests.CoursUpdateDto;
import org.simplon.permisdrive.dtos.requests.SujetRequestDto;
import org.simplon.permisdrive.dtos.requests.SujetUpdateDto;
import org.simplon.permisdrive.dtos.responses.CoursResponseDto;
import org.simplon.permisdrive.dtos.responses.SujetResponseDto;
import org.simplon.permisdrive.models.entities.Cours;
import org.simplon.permisdrive.models.entities.Sujet;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICoursService {

    Page<CoursResponseDto> getAllCours(Pageable pageable);

    CoursResponseDto getCoursDtoById(Long coursId) throws ChangeSetPersister.NotFoundException;

    Cours getCoursById(Long coursId) throws ChangeSetPersister.NotFoundException;

    CoursResponseDto createCours(CoursRequestDto coursRequestDto);

    CoursResponseDto updateCours(Long coursId, CoursUpdateDto coursUpdateDto) throws ChangeSetPersister.NotFoundException;

    void deleteCours(Long coursId) throws ChangeSetPersister.NotFoundException;
}
