package org.simplon.permisdrive.controllers;


import org.simplon.permisdrive.dtos.requests.CoursRequestDto;
import org.simplon.permisdrive.dtos.requests.CoursUpdateDto;
import org.simplon.permisdrive.dtos.responses.CoursResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ICoursController {

    ResponseEntity<Page<CoursResponseDto>> getAllCours(
            int page, int size, String sortByCategory, String sort
    );

    ResponseEntity<CoursResponseDto> getCoursById(Long coursId) throws ChangeSetPersister.NotFoundException;

    ResponseEntity<CoursResponseDto> createCours(CoursRequestDto coursRequestDto);

    ResponseEntity<CoursResponseDto> updateCours(Long coursId, CoursUpdateDto coursUpdateDto) throws ChangeSetPersister.NotFoundException;

    ResponseEntity<String> deleteCours(Long coursId) throws ChangeSetPersister.NotFoundException;
}
