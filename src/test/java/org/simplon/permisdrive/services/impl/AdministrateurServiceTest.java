package org.simplon.permisdrive.services.impl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.simplon.permisdrive.dtos.requests.AdministrateurRequestDto;
import org.simplon.permisdrive.dtos.requests.AdministrateurUpdateDto;
import org.simplon.permisdrive.dtos.responses.AdministrateurResponseDto;
import org.simplon.permisdrive.mapper.IAdministrateurMapper;
import org.simplon.permisdrive.models.entities.Administrateur;
import org.simplon.permisdrive.repositories.IAdministrateurRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdministrateurServiceTest {

    @InjectMocks
    AdministrateurService administrateurService;

    @Mock
    IAdministrateurRepository iAdministrateurRepository;

    @Mock
    IAdministrateurMapper iAdministrateurMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllAdministrateurs() {
        Page<Administrateur> administrateurs = new PageImpl<>(Collections.emptyList());
        when(iAdministrateurRepository.findByDeletedFalse(PageRequest.of(0, 10))).thenReturn(administrateurs);

        Page<AdministrateurResponseDto> response = administrateurService.getAllAdministrateurs(PageRequest.of(0, 10));

        assertEquals(administrateurs.map(iAdministrateurMapper::toResponseDto), response);
        verify(iAdministrateurRepository, times(1)).findByDeletedFalse(PageRequest.of(0, 10));
    }

    @Test
    void shouldReturnAdministrateurById() throws Exception {
        Administrateur administrateur = new Administrateur();
        when(iAdministrateurRepository.findByAdministrateurIdAndDeletedFalse(1L)).thenReturn(java.util.Optional.of(administrateur));

        Administrateur response = administrateurService.getAdministrateurById(1L);

        assertEquals(administrateur, response);
        verify(iAdministrateurRepository, times(1)).findByAdministrateurIdAndDeletedFalse(1L);
    }

    @Test
    void shouldCreateAdministrateur() {
        AdministrateurRequestDto administrateurRequestDto = new AdministrateurRequestDto();
        Administrateur administrateur = new Administrateur();
        when(iAdministrateurMapper.toEntity(administrateurRequestDto)).thenReturn(administrateur);
        when(iAdministrateurRepository.save(administrateur)).thenReturn(administrateur);

        AdministrateurResponseDto response = administrateurService.createAdministrateur(administrateurRequestDto);

        assertEquals(iAdministrateurMapper.toResponseDto(administrateur), response);
        verify(iAdministrateurRepository, times(1)).save(administrateur);
    }

    @Test
    void shouldUpdateAdministrateur() throws Exception {
        AdministrateurUpdateDto administrateurUpdateDto = new AdministrateurUpdateDto();
        Administrateur existingAdministrateur = new Administrateur();
        when(iAdministrateurRepository.findByAdministrateurIdAndDeletedFalse(1L)).thenReturn(java.util.Optional.of(existingAdministrateur));
        when(iAdministrateurMapper.partialUpdate(administrateurUpdateDto, existingAdministrateur)).thenReturn(existingAdministrateur);
        when(iAdministrateurRepository.save(existingAdministrateur)).thenReturn(existingAdministrateur);

        AdministrateurResponseDto response = administrateurService.updateAdministrateur(1L, administrateurUpdateDto);

        assertEquals(iAdministrateurMapper.toResponseDto(existingAdministrateur), response);
        verify(iAdministrateurRepository, times(1)).save(existingAdministrateur);
    }

    @Test
    void shouldDeleteAdministrateur() throws Exception {
        Administrateur administrateur = new Administrateur();
        when(iAdministrateurRepository.findByAdministrateurIdAndDeletedFalse(1L)).thenReturn(java.util.Optional.of(administrateur));
        when(iAdministrateurRepository.save(administrateur)).thenReturn(administrateur);
        administrateurService.deleteAdministrateur(1L);

        verify(iAdministrateurRepository, times(1)).save(administrateur);
    }
}