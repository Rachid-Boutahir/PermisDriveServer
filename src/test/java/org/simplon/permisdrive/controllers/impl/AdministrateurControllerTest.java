package org.simplon.permisdrive.controllers.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.simplon.permisdrive.dtos.requests.AdministrateurRequestDto;
import org.simplon.permisdrive.dtos.requests.AdministrateurUpdateDto;
import org.simplon.permisdrive.dtos.responses.AdministrateurResponseDto;
import org.simplon.permisdrive.services.IAdministrateurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdministrateurControllerTest {

    @InjectMocks
    AdministrateurController administrateurController;

    @Mock
    IAdministrateurService iAdministrateurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void shouldReturnAdministrateurById() throws Exception {
        AdministrateurResponseDto administrateurResponseDto = new AdministrateurResponseDto();
        when(iAdministrateurService.getAdministrateurDtoById(1L)).thenReturn(administrateurResponseDto);

        ResponseEntity<AdministrateurResponseDto> response = administrateurController.getAdministrateurById(1L);

        assertEquals(administrateurResponseDto, response.getBody());
        verify(iAdministrateurService, times(1)).getAdministrateurDtoById(1L);
    }

    @Test
    void shouldCreateAdministrateur() {
        AdministrateurRequestDto administrateurRequestDto = new AdministrateurRequestDto();
        AdministrateurResponseDto administrateurResponseDto = new AdministrateurResponseDto();
        when(iAdministrateurService.createAdministrateur(administrateurRequestDto)).thenReturn(administrateurResponseDto);

        ResponseEntity<AdministrateurResponseDto> response = administrateurController.createAdministrateur(administrateurRequestDto);

        assertEquals(administrateurResponseDto, response.getBody());
        verify(iAdministrateurService, times(1)).createAdministrateur(administrateurRequestDto);
    }

    @Test
    void shouldUpdateAdministrateur() throws Exception {
        AdministrateurUpdateDto administrateurUpdatetDto = new AdministrateurUpdateDto();
        AdministrateurResponseDto administrateurResponseDto = new AdministrateurResponseDto();
        when(iAdministrateurService.updateAdministrateur(1L, administrateurUpdatetDto)).thenReturn(administrateurResponseDto);

        ResponseEntity<AdministrateurResponseDto> response = administrateurController.updateAdministrateur(1L, administrateurUpdatetDto);

        assertEquals(administrateurResponseDto, response.getBody());
        verify(iAdministrateurService, times(1)).updateAdministrateur(1L, administrateurUpdatetDto);
    }

    @Test
    void shouldDeleteAdministrateur() throws Exception {
        doNothing().when(iAdministrateurService).deleteAdministrateur(1L);

        ResponseEntity<String> response = administrateurController.deleteAdministrateur(1L);

        assertEquals("Administrateur avec id:1 a été supprimé avec succès!", response.getBody());
        verify(iAdministrateurService, times(1)).deleteAdministrateur(1L);
    }
}